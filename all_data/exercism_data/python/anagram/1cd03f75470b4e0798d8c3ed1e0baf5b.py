#anagram.py
# smart trams...?????
import collections


def detect_anagrams(word, possibilities):
    matches = []
    word_set = collections.Counter(list(word.lower()))# transform into char:count dict
    possibilities_set = {possible: collections.Counter(possible.lower()) for possible in possibilities}
    for possible_match in possibilities_set:
        if possibilities_set[possible_match] == word_set and possible_match.lower() != word.lower():
            matches.insert(0, possible_match)
    return matches
