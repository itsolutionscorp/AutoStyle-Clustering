def word_count(words):
    seen = dict()
    for word in words.split():
        if not word in seen:
            seen[word] = 1
        else:
            seen[word] += 1
    return seen
