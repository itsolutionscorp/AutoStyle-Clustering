__author__ = 'cameron'
def detect_anagrams(word, anagram):
    matches = ''
    sortedword = sorted(word)
    for alt in anagram:
        if sortedword == sorted(alt):
            matches += alt + ' '
    return matches.split()
