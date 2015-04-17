def word_count(phrase):
    result = {}
    words = phrase.split()
    return {word : words.count(word) for word in words} 
