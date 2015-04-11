#
# Returns all anagrams of a word from a list of possible anagrams
#

def detect_anagrams(word, pool):
    return [i for i in pool if sorted(i.lower()) == sorted(word.lower())
        and i.lower() != word.lower()]
