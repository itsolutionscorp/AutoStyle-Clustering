from collections import Counter
def word_count(sentence):
    word = sentence.split()
    return(Counter(word))
