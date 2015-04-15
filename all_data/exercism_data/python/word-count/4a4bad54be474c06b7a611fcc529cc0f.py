def word_count(phrase):

    words = phrase.split()
    count = {}
    for word in words:
        if count.has_key(word):
            count[word] += 1
        else:
            count[word] = 1        
    return count
    
