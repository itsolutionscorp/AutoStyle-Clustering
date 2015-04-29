def word_count(phrase):
    #count how many of each word is in phrase
    #return each word with a colon and how many instances of word are in phrase

    d = dict()

    for word in phrase.strip().split():
        if word in d:
            d[word] += 1
        else:
            d[word] = 1
            
    return d
