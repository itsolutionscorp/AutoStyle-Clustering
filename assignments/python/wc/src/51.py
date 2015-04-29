def word_count(sentence):
    fixed = sentence.split()
    compare_to = []
    for word in fixed:
        if word not in compare_to:
            compare_to.append(word)
    d = {}
    for word in fixed:
        d[word] = fixed.count(word)
    return d
