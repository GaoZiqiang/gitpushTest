package cn.edu.sdut.softlab.rooms;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.inject.Qualifier;

/**
 * 删除房间
 * @author subaochen
 *
 */
@Qualifier
@Target({FIELD,PARAMETER})
@Retention(RUNTIME)
public @interface Remove {
}
