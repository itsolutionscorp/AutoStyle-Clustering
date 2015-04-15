
def detect_anagrams(word, test_list):
    wordlow = word.lower()
    look_for = sorted(wordlow)
    anagrams = []
    for candidate in test_list:
        candidatelow = candidate.lower()
        if wordlow != candidatelow:
            if look_for == sorted(candidatelow):
                anagrams.append(candidate)
    return anagrams
