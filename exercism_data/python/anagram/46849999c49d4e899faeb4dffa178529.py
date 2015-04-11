def detect_anagrams(anagram, words):
    anagrams = []
    anagramChars = sorted(list(anagram.lower()))

    [anagrams.append(word) for word in words if not anagram.lower() == word.lower() and (anagramChars == sorted(list(word.lower())))]
    
    return anagrams
