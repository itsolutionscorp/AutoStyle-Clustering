import collections
def word_count(wordlist):
    words = collections.Counter()
    for word in wordlist.split():
        words[word] += 1
    return words
