def word_count(something):
    all_words = something.split()
    word_totals = {}
    for word in all_words:
        if word not in word_totals:
            word_totals[word] = 0
        word_totals[word] += 1
    return (word_totals)
