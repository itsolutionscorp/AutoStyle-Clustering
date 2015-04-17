from collections import OrderedDict
def word_count(phrase):
    """Takes in a phrase and returns a dictionary listing
       each word in the phrase and the number of times it
       appears.
    """
    words = phrase.split()
    deDupedWords = OrderedDict.fromkeys(words)
    wordCount = {}
    for element in deDupedWords:
        wordCount.update({element: words.count(element)})
    return wordCount
