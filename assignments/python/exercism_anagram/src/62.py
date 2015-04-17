def detect_anagrams(inputWord, anagramCandidates):
    inputWordSorted = sorted(inputWord.lower())
    result = []
    for candidates in anagramCandidates:
        if inputWordSorted == sorted(candidates.lower()) and inputWord.lower() != candidates.lower():
            result.append(candidates)
    return result
