def word_count(words):
    dictionary = dict()
    stuff = str()
    words = words.lower()
    array = words.split(' ')
    for word in array:
        word = word.replace(',', '')
        word = word.replace('!', '')
        word = word.replace('&', '')
        word = word.replace('@', '')
        word = word.replace('^', '')
        word = word.replace('%', '')
        word = word.replace('$', '')
        try:
            dictionary[word]
            dictionary[word] += 1
        except KeyError:
            dictionary[word] = 1
    return dictionary
