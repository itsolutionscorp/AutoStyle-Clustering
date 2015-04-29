def word_count(text):
    count = {}
    words = text.split()
    for word in words:
        count[word] = count.get(word, 0) + 1
    return count
    
