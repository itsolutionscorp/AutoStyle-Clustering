from collections import OrderedDict                         # Import this so we can remove duplicates from a list
def word_count(phrase):
   words = phrase.split()                                   # Take input phrase and split it into individual words
   deDupedWords = OrderedDict.fromkeys(words)               # Take words list, and remove duplicates
   for element in deDupedWords:                             
       print element + ": " + str(words.count(element))     # Print out the word and the count of each word
