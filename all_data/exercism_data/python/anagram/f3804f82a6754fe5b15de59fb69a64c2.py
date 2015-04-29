def detect_anagrams(anagram, words):
    return [word for word in words if ''.join(sorted(anagram.lower())) == ''.join(sorted(word.lower())) and (anagram.lower() != word.lower())]
