from collections import defaultdict

def detect_anagrams(source, targets):
    return [ x for x in targets if 
                   x.lower() != source.lower() and
                   getLetterFrequencies(source.lower()) ==
                   getLetterFrequencies(x.lower()) ]

def getLetterFrequencies(word):
    freq = defaultdict(int)
    for letter in word:
        freq[letter] += 1
    return freq
