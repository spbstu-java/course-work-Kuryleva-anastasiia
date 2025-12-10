package Lab_2;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class MethodCaller {
    private StringBuilder output = new StringBuilder();
    
    public String callAnnotatedMethods(MyAnnotatedClass obj) {
        output.setLength(0); // Очищаем предыдущий вывод
        
        Method[] methods = MyAnnotatedClass.class.getDeclaredMethods();

        for (Method method : methods) {
            if(!Modifier.isPublic(method.getModifiers())) {
                if (method.isAnnotationPresent(CallCounter.class)) {
                    CallCounter annotation = method.getAnnotation(CallCounter.class);
                    int callCount = annotation.value();

                    method.setAccessible(true);

                    output.append("\n=== Метод: ").append(method.getName())
                          .append(", количество вызовов: ").append(callCount)
                          .append(" ===");

                    for (int i = 1; i <= callCount; i++) {
                        try {
                            Object[] params = generateParameters(method.getParameterTypes());

                            output.append("\nВызов ").append(i).append(": ");
                            Object result = method.invoke(obj, params);
                            
                            if (result != null) {
                                output.append(" результат = ").append(result);
                            }

                        } catch (Exception e) {
                            output.append("\nОшибка при вызове: ").append(e.getMessage());
                        }
                    }
                    output.append("\n");
                }
            }
        }
        
        return output.toString();
    }

    private Object[] generateParameters(Class<?>[] paramTypes) {
        Object[] parameters = new Object[paramTypes.length];

        for (int i = 0; i < paramTypes.length; i++) {
            parameters[i] = getDefaultValue(paramTypes[i], i);
        }

        return parameters;
    }

    private Object getDefaultValue(Class<?> type, int paramIndex) {
        if (type == int.class || type == Integer.class) {
            return 1 + paramIndex;
        } else if (type == double.class || type == Double.class) {
            return 2.5 + paramIndex;
        } else if (type == boolean.class || type == Boolean.class) {
            return paramIndex % 2 == 0;
        } else if (type == String.class) {
            return "просто строка " + (paramIndex + 1);
        } else {
            return null;
        }
    }
}