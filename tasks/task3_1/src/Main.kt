// Task 3.1: command line arguments


import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if (args.size < 2) {
        println("Error: please provide at least 2 arguments!")
        exitProcess(1)
    }
    println(args[0])
    println(args[1])
    
}
