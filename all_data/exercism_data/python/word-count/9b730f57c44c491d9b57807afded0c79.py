from collections import defaultdict

def word_count(sentence):
    wordcount = defaultdict(int)
    for eachWord in sentence.split():
        wordcount[eachWord] += 1
    return wordcount
