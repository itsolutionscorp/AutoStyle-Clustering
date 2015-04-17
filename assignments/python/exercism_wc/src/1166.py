from collections import OrderedDict                         # Import this so we can remove duplicates from a list
def word_count(phrase):
   words = phrase.split()                                   # Take input phrase and split it into individual words
   deDupedWords = OrderedDict.fromkeys(words)               # Take words list, and remove duplicates
   wordCount = {}                                           # Dictionary to hold the words and their counts
   for element in deDupedWords:                             
       wordCount.update({element:words.count(element)})       # Add the words plus their count to the wordCount dictionary
   return wordCount
