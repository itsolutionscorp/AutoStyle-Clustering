def word_count(words):
    dict = {}
    for word in words.split():
        if not dict.has_key(word):
            dict[word] = 1 
        else:
            dict[word] += 1
    return dict
