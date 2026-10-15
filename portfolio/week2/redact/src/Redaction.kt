fun redact(doc: String, target: String, replacement: Char = 'X'): String {
    if (target.isEmpty()) return doc
    val mask = replacement.toString().repeat(target.length)
    return doc.replace(target, mask)
}