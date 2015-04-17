def word_count(text):
    counts = {}
    for item in text.split():
        if item in counts:
            counts[item] += 1
        else:
            counts[item] = 1
    return counts
