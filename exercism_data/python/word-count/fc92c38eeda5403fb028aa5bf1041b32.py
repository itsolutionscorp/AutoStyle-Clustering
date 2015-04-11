def word_count(s):
    words = {}

    for word in s.split():
        if word in words:
            words[word] += 1
        else:
            words[word] = 1

    return words
