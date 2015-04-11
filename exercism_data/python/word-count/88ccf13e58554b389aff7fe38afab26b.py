
#words, words, words!

def word_count(words):
    countDict = dict() ##dictionary for storing output count.
    [checkCount(word, countDict) for word in words.split()] ## list comprehension, running checkCount on every word in the list resulting from words.split()
    return countDict


def checkCount(word, dictionary): ##function for counting words and updating the output dictionary
    if (word in dictionary.keys()):
        dictionary[word] += 1
    else:
        dictionary[word] = 1
    
