def word_count(input):
    result = {}
    words = [filter(str.isalnum, w.lower()) for w in input.split()]
    words = filter(None, words)
    
    for word in words:
        count = result.get(word, 0) + 1
        result[word] = count
    return result
