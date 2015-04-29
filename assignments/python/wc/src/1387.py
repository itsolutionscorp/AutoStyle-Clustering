from collections import Counter
def word_count(sentence):
    wordcounter = Counter(sentence.split())
    return wordcounter
