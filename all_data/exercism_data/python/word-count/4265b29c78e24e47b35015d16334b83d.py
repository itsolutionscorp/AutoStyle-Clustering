def word_count(phrase):
    counts = {}
    word_list  = phrase.split()
    for word in word_list:
        counts_so_far = counts.setdefault(word, 0) # inserts the word or gets the value
        counts[word] = counts_so_far + 1
    return counts
