#
# Word Count
#
# Counts the number of occurences of each word of a string.
# Exercism.io code challange
#
# Joshua Ferdaszewski
# January 10, 2015
#

import string

def word_count(countme):
    word_num = {}
    for word in countme.split():
        word_num[word] = countme.split().count(word)

    return word_num
