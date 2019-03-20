package laplacian.model.metamodel.model
import com.github.jknack.handlebars.Context

/**
 * A container for records of entity
 */
class EntityList(
    list: List<Entity>,
    val context: Context
) : List<Entity> by list {
    /**
     * トップレベルエンティティの一覧
     */
    val topLevel: List<Entity>
        get() {
            return filter{ it.topLevel }
        }
    /**
     * The top level entities which are included in the same namespace.
     */
    val topLevelInNamespace: List<Entity>
        get() {
            return inNamespace.filter{ it.topLevel }
        }
    val inNamespace: List<Entity>
        get() = filter {
            it.namespace.startsWith(context.get("project.namespace") as String)
        }
}
