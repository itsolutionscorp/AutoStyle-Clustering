def word_count(to_count):
    
    lst = list()
    words_counted = dict()

    for words in to_count.split():
        lst.append(words)
    
    for items in lst:
        words_counted[items] = words_counted.get(items, 0) + 1
    
    return words_counted
