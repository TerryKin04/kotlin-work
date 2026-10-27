// COMP2850 Portfolio: Week 4
// ToDoList class

import kotlinx.datetime.LocalDate
import kotlin.io.path.Path
import kotlin.io.path.forEachLine
import kotlin.io.path.writer

class ToDoList() {
    private val items = mutableListOf<ToDoItem>()

    val size get() = items.size
    val notStarted get() = items.count { it.status == ItemStatus.NotStarted }
    val inProgress get() = items.count { it.status == ItemStatus.InProgress }
    val done get() = items.count { it.status == ItemStatus.Done }
    val overdue get() = items.count { it.isOverdue() }

    constructor(filename: String) : this() {
        load(filename)
    }

    fun load(filename: String) {
        Path(filename).forEachLine { line ->
            if (line.isNotBlank()) {
                val parts = line.split(":", limit = 3).map { it.trim() }
                if (parts.size == 3) {
                    val date = LocalDate.parse(parts[0])
                    val status = ItemStatus.valueOf(parts[1])
                    val description = parts[2]
                    items.add(ToDoItem(date, status, description))
                }
            }
        }
    }

    fun save(filename: String) {
        Path(filename).writer().use { out ->
            for (item in items) {
                out.write("$item\n")
            }
        }
    }

    fun add(vararg item: ToDoItem) {
        items.addAll(item)
    }

    fun deleteDone(): Boolean {
        return items.removeIf { it.status == ItemStatus.Done }
    }

    fun getItems(status: ItemStatus? = null): List<ToDoItem> {
        return if (status == null) items.toList() else items.filter { it.status == status }
    }

    fun getItemsDueOn(date: LocalDate): List<ToDoItem> {
        return items.filter { it.dueDate == date }
    }
}