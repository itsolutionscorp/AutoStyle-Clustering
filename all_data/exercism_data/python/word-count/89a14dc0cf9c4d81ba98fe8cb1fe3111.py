__author__ = 'samuelbarthelemy'

def doNothing():
    return

def cleanSentence(sentence):

    if sentence.__contains__("\n"):
        sentence = sentence.replace("\n", " ")

    return sentence

def word_count(sentence):
    newSentence = cleanSentence(sentence)
    wordArray = newSentence.split(' ')
    wordDictionary = {}


    for word in wordArray:
        if word == "":
            doNothing()
        elif wordDictionary.has_key(word):
            wordDictionary[word] += 1
        else:
            wordDictionary[word] = 1
    return wordDictionary
