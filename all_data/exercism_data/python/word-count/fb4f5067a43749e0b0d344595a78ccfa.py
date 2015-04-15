def word_count(sentence):
    
    split = sentence.split()
    words = {}
    
    for s in set(split):
        words[s] = split.count(s)
        
    return words
