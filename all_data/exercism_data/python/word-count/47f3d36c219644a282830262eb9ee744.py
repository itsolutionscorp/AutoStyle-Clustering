#
# Word Count
#
# Counts the number of occurences of each word of a phrase.
# Exercism.io code challange
#
# Joshua Ferdaszewski
# January 10, 2015
#

import collections

def word_count(countme):
    return collections.Counter(countme.split())
