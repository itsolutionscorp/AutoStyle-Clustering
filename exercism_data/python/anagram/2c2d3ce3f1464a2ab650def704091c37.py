# -*- coding: utf-8 -*-

def sorted_word(word):
    return ''.join(sorted(word.upper()))

def is_anagram(word, anagram):
    if word.upper() == anagram.upper():
        return False
    return  sorted_word(word) == sorted_word(anagram)

def detect_anagrams(word, anagrams):
    return [x for x in anagrams if is_anagram(word, x)]
