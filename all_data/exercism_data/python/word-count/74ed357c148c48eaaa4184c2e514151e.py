import re

def word_count(phrase):
    counts = {}
    wordlist = phrase.split()
    for word in wordlist:
        word = re.sub('[\W]+', '', word)
        word = word.lower().strip()
        if (word in counts) and word:
            counts[word] += 1
        elif word:
            counts[word] = 1
    return counts
