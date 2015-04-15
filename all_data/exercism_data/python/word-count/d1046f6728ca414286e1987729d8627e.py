def word_count(word):
    dictionary = dict()
    for words in word.split():
        dictionary[words] = dictionary.setdefault(words, 0)+1
    return dictionary
