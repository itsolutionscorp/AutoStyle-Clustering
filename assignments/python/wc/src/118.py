import re
import string
def word_count(words):
    words = re.sub('[\n]', ' ', words)
    wordlist = words.split(' ')
    wordlist = filter(bool, wordlist)
    unique_words = set(wordlist)
    counts = {}
    for word in wordlist:
        counts[word] = 0
    for word in wordlist:
        if word in unique_words:
            counts[word] += 1
    return counts
