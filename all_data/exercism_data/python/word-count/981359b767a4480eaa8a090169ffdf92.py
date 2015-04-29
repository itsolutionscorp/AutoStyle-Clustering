#
# Returns the number of occurences of a word in a given string
#

def word_count(phrase):
    phrase = phrase.split()
    occurences = {}

    for word in set(phrase):
        occurences[word] = phrase.count(word)

    return occurences
