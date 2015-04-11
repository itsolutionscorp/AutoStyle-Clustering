def word_count(phrase):
    words = phrase.split();
    d = {}
    
    for i in range(len(words)):
        word = words[i]
        if words.index(word) == i:
            d[word] = words.count(word)
    
    return d
