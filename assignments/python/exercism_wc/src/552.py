def word_count(string):
    return [ [None for words_list in [string.split()]], { word: words_list.count(word) for word in set(words_list) } ][-1]
