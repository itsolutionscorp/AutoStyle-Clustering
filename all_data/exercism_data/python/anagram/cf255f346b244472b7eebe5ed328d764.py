import string


def detect_anagrams(match: string, words: []) -> [string]:
    return [word for word in words if match.lower() != word.lower() and sorted(match.lower()) == sorted(word.lower())]
