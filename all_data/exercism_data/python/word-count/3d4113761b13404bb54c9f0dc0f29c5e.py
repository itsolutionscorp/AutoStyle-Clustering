def word_count(sentance):
    words = sentance.split()    
    return {word:words.count(word) for word in set(words)}         
