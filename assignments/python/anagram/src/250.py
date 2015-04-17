def detect_anagrams(original, candidates):
    anagrams = []
    for word in candidates:
        if sorted(original.lower()) == sorted(word.lower()) and original.lower() != word.lower():
            anagrams.append(word) 
    return anagrams
