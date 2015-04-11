def detect_anagrams(word, candidates):
    anagrams = []
    # basic idea: if you sort the characters in anagrams alphabetically then they all look the same
    sortedWord = sorted(word.lower(), key=str.lower)
    
    for candidate in candidates:
        if candidate.lower() == word.lower(): # then it's the same word, not an anagram
            continue
        sortedCandidate = sorted(candidate.lower(), key=str.lower)
        if sortedCandidate == sortedWord:
            anagrams.append(candidate)
    return anagrams
