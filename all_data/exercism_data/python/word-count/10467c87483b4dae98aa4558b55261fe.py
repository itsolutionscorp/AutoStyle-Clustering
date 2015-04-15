#-------------------------------------------------------------------------------
# Name:        wordcount.py
# Purpose:     Count the number of occurences for each word in a given phrase.
#
# Author:      Mihaela
#
# Created:     27.09.2014
#
#-------------------------------------------------------------------------------

def word_count(phrase):
    # Create the returned dictionary
    answer = {}
    # Identify the words used in the given string
    words_ident = phrase.split()
    for word in words_ident:
        # For each word look for the number of times it's used in the string
        no = words_ident.count(word)
        # Add the new word to the dictionary
        answer[word] = no
    # Return the filled dictionary
    return answer
