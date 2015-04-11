def word_count(to_count):
    
    words_counted = {}

    for words in to_count.split():
        words_counted[words] = words_counted.get(words, 0) + 1
    
    return words_counted
