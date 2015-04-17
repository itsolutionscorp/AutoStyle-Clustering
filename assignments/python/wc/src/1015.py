def word_count(string):
    counts = {}
    lines = string.splitlines()
    words = reduce(lambda memo, line: memo + line.split(" "), lines, [])
    words = [word for word in words if word != ""]
    for word in words:
        if word in counts:
            counts[word] += 1
        else:
            counts[word] = 1
    return counts
