from collections import Counter


def detect_anagrams(word, anagrams):

    word_sorted = sorted(word.lower())

    results = [a for a in anagrams 
               if (sorted(a.lower()) == word_sorted 
                       and a.lower() != word.lower())]

    return results
