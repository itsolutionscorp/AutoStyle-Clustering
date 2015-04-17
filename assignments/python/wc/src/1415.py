from collections import Counter
def word_count(sentence):
    wordcounter = Counter()
    wordslist = sentence.split()
    for word in wordslist:
        wordcounter[word] += 1
    return wordcounter
