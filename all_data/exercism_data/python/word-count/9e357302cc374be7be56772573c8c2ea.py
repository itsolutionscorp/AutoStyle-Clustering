__author__ = 'samuelbarthelemy'

def doNothing():
    return


def word_count(sentence):
    sentence = sentence.replace("\n", " ")
    wordArray = sentence.split(' ')
    wordDictionary = {}


    for word in wordArray:
        if word == "":
            doNothing()
        elif wordDictionary.has_key(word):
            wordDictionary[word] += 1
        else:
            wordDictionary[word] = 1
    return wordDictionary
