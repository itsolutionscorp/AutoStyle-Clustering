def word_count(s):
    words = s.split()
    unique = set(words)
    word_dict = {}
    for word in unique:
        word_dict[word] = words.count(word)
    return word_dict
