# wordcount.py
# iterative
import string

def word_prep(text):
    """
        Takes a string as an argument.
        Returns a list of the words stripped of escape codes.
    """
    escCodes = ['\b', '\t', '\n', '\a', '\r'] # escape code set
    for code in escCodes:
        if code in text:
            text = text.replace(code, ' ')
    
    # return list from text separated by spaces
    return text.split(' ')
    

def word_count(text):
    """
        Takes a string as an argument.
        Returns a dictionary with the word and number
        of occurrences of the word as key/value pairs.
    """
    
    newList = word_prep(text)
    wordDict = {}

    # iterate through list
    for word in newList:
        if len(word) != 0: # ignores excess spaces
            # add word and number of occurrences of word to dictionary
            wordDict[word] = newList.count(word)

    return wordDict

