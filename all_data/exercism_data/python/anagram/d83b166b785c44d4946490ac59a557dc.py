__author__ = 'jimblackler'


def normalized(word):
    return ''.join(sorted(list(word.lower())))


def detect_anagrams(target, words): 
    return [word for word in words if normalized(target) == normalized(word) and target.lower() != word.lower()]
