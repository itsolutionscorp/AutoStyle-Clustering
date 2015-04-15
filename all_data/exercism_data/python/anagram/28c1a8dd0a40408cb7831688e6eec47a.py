#!/usr/bin/python
def detect_anagrams(word, word_list):
    letter_count = lambda w: tuple(sorted(dict(zip(list(w),[w.count(x) for x in list(w)])).items()))
    return filter(lambda x: letter_count(x.lower()) == letter_count(word.lower()) and not x.lower()==word.lower(), word_list)
