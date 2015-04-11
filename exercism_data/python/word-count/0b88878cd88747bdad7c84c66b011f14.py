def word_count(aString):
    aString = aString.strip()
    d = {}
    list_of_words = aString.split()
    for word in list_of_words:
        if not d.get(word):
            d[word] = 1
        else:
            d[word] = d.get(word) + 1
    return d
