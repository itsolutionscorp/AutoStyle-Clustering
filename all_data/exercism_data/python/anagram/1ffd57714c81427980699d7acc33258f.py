import string


def detect_anagrams(anagram: string, words: []) -> []:
    return [word for word in words if anagram.lower() != word.lower() and sorted(anagram.lower()) == sorted(word.lower())]
