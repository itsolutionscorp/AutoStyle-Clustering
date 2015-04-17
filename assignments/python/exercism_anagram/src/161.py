from string import maketrans 
def detect_anagrams(word, candidates):
    word_sorted = ''.join(sorted(word.lower()))
    anagrams = [];
    for candidate in candidates:
        if word.lower() != candidate.lower() and word_sorted == ''.join(sorted(candidate.lower())):
            anagrams.append(candidate)
    return anagrams
