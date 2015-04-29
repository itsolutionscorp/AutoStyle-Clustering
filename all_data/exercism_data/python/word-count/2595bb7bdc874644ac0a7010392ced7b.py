__author__ = 'octowl'

def word_count(phrase):
    wordCountDictionary = {}

    for word in phrase.split():
        if word in wordCountDictionary:
            wordCountDictionary[word] += 1
        else:
            wordCountDictionary[word] = 1

    return wordCountDictionary
