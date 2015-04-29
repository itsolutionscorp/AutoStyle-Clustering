# First take the input phrase and split it into a list of its
# constituent words, words[]. Then, take that list, and
# create a second list that removes all of the duplicates,
# deDupedWords[]. That way, we can see just the individual
# words that make up the phrase, regardless of how many times
# they occur. We then take that second, deduped list and use
# the elemets of that list to count their occurence in the
# first list that contains everything from the input phrase.
# Then, add the words and their counts to the wordCount
# dictionary and return that dictionary.

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
