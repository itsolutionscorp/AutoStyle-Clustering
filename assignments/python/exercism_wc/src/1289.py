import string
def word_count(phrase):
    tally = {}
    words = map(lambda word: word.lower().strip(string.punctuation), phrase.split())
    for word in words:
        if word: # ignore whitespace words
            try:
                tally[word] += 1
            except KeyError:
                tally[word] = 1
    return tally
