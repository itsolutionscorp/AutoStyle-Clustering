def word_count(parse):
    words = str(parse)
    list = words.split()
    items = tuple(list)
    for word in items:
        count = list.count(word)
        number = str(count)
        return word + ": " + number
