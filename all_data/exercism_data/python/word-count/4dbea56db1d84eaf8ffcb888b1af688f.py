def word_count(something):
    #seperate words
    all_words = something.split()
    #hold individual unique words
    word_totals = {}

    for word in all_words:
        if word not in word_totals:
            #add unique word
            word_totals[word] = 0
        #add to total
        word_totals[word] += 1

    return (word_totals)
