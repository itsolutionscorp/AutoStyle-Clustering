from collections import defaultdict

def word_count(string):
    counts = defaultdict(int)

    lines = string.splitlines()
    words = reduce(lambda memo, line: memo + line.split(" "), lines, [])
    words = [word for word in words if word != ""]

    for word in words:
        counts[word] += 1

    return counts
