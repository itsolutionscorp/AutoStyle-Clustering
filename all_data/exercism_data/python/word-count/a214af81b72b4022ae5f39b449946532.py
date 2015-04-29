def word_count(s):
    words = {}
    for word in s.split():
        try:
            count = words[word]
            count += 1
            words[word] = count
        except KeyError:
            words[word] = 1
    return words
