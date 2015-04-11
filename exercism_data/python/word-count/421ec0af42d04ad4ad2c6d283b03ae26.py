def word_count(phrase):
    '''Returns dict with word counts for a given phrase.'''
    count = {}
    for word in phrase.split():
        count.setdefault(word, 0)
        count[word] += 1

    return count
