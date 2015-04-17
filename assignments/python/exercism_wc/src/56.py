'''
wordcount.py
count occurrences of words in a phrase
'''
def word_count(phrase):
    '''
    Count the words in the given phrase
    @param phrase: the phrase to be parsed
    @returns: dict of words with their count as values
    '''
    counts = {}
    for word in phrase.split():
        if word not in counts:
            counts[word] = 0
        counts[word] += 1
    return counts
