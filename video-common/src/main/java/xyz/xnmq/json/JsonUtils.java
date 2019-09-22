package xyz.xnmq.json;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.introspect.Annotated;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;
import org.codehaus.jackson.map.introspect.JacksonAnnotationIntrospector;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class JsonUtils {

    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    private static final ObjectMapper mapper;

    public ObjectMapper getMapper() {
        return mapper;
    }

    static {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        mapper = new ObjectMapper();
        //设置输入时忽略JSON字符串中存在而Java对象实际没有的属性
        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //只输出非空属性到Json字符串.
        mapper.setSerializationInclusion(JsonSerialize.Inclusion.ALWAYS);

        mapper.setDateFormat(dateFormat);
        mapper.setAnnotationIntrospector(new JacksonAnnotationIntrospector() {
            @Override
            public Object findSerializer(Annotated annotated) {
                if (annotated instanceof AnnotatedMethod) {
                    AnnotatedElement m = annotated.getAnnotated();
                    DateTimeFormat an = m.getAnnotation(DateTimeFormat.class);
                    if (an != null) {
                        if (!DEFAULT_DATE_FORMAT.equals(an.pattern())) {
                            return new JsonDateSerializer(an.pattern());
                        }
                    }
                }
                return super.findSerializer(annotated);
            }
        });
    }

    public static String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException("转换json字符失败!");
        }
    }

    public static <T> T toObject(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException("将json字符转换为对象时失败!");
        }
    }

    public static class JsonDateSerializer extends JsonSerializer<Date> {
        private SimpleDateFormat dateFormat;

        public JsonDateSerializer(String format) {
            dateFormat = new SimpleDateFormat(format);
        }

        @Override
        public void serialize(Date date, JsonGenerator gen, SerializerProvider provider)
                throws IOException, JsonProcessingException {
            String value = dateFormat.format(date);
            gen.writeString(value);
        }
    }
}  