"""
loop through each possible_anagram in the list provided
checks are case-insensitive
if the word is not identical to the possible_anagram and
the sorted word matches the sorted possible_anagram
add the possible_anagram to the answer list
"""
def detect_anagrams(word, possible_anagrams):
    anagrams = []
    for possible_anagram in possible_anagrams:
        if word.lower() != possible_anagram.lower()  and \
        sorted(word.lower()) == sorted(possible_anagram.lower()):
                anagrams.append(possible_anagram)
    return anagrams
