def word_count(words):    
    words= words.split()    
    return {word: words.count(word) for word in words}    
