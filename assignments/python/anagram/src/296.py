def detect_anagrams(aword, words):
    anagrams = []
    for word in words:
        if sorted(list(aword.lower())) == sorted(list(word.lower())) and aword.lower() != word.lower():
            anagrams.append(word)
    return anagrams
