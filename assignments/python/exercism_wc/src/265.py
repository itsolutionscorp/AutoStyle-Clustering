from collections import Counter
def word_count(sentence):
    freqs = Counter(sentence.split())
    return(freqs)
