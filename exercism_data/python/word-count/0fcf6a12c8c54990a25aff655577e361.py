def word_count(phrase):
    d = dict()
    s = phrase.split()
    for w in s:
        if w in d:
            d[w] += 1
        else:
            d[w] = 1
    return d
        
    
