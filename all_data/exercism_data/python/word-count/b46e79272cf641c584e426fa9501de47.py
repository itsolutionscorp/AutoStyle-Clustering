def word_count(parse):

    #convert input to string
    words = str(parse)

    #split into list of individual items
    list = words.split()

    #conver items to tuple
    items = tuple(list)

    #iterate over tuple and print count of each unique word
    for word in items:
        count = list.count(word)
        number = str(count)
        return word + ": " + number

#stuff = raw_input('enter words: ')
#print word_count(stuff)
