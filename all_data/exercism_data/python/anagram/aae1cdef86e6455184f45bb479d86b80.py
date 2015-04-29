def detect_anagrams(word, candidates):
    anagram_list = []
    word = word.lower()
    for original_candidate in candidates:
        candidate = original_candidate.lower()
        if candidate == word:
            continue
        candidate_chars = list(candidate)
        equal_chars = True
        for char in candidate_chars:
            if char not in word:
                equal_chars = False
            elif word.count(char) != candidate.count(char):
                equal_chars = False

        if equal_chars and len(candidate) == len(word):
            anagram_list.append(original_candidate)

    return anagram_list
