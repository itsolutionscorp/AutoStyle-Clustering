# I think I may have deleted a phrase from my 'banana' test.
# This submission is to see if others are making special provision
# for the banana exercise or if I messed something up.

def detect_anagrams(seed, comparisons):
    anagrams = list(word for word in comparisons if sorted(word.lower()) == sorted(seed.lower()))
        return anagrams
