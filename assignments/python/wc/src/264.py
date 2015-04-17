__author__ = 'samuelbarthelemy'
def word_count(sentence):
    sentence = sentence.replace("\n", " ")
    wordArray = sentence.split()
    wordDictionary = {}
    for word in wordArray:
        if wordDictionary.has_key(word):
            wordDictionary[word] += 1
        else:
            wordDictionary[word] = 1
    return wordDictionary
