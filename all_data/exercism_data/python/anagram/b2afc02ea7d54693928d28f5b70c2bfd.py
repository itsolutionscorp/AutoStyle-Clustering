__author__ = 'emiller42'


def detect_anagrams(target_word, potential_anagrams):

    # Container for matches
    anagrams = []

    # Turn target_word into a sorted list of lowercase characters
    target_word_lower = target_word.lower()
    target_word_sorted = sorted(target_word_lower)

    # iterate through potential_anagrams
    # validate the potential isn't the same word as the target
    # then sort and compare to the target letter sequence
    for potential_anagram in potential_anagrams:
        potential_anagram_lower = potential_anagram.lower()
        if target_word_lower != potential_anagram_lower:
            if target_word_sorted == sorted(potential_anagram_lower):
                anagrams.append(potential_anagram)

    return anagrams
