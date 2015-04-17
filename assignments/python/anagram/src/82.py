def detect_anagrams(word, candidates):
    word_sorted = list(word.lower())
    word_sorted.sort()
    anagram_list = []
    for candidate in candidates:
        if candidate.lower() != word.lower():
            candidate_sorted = list(candidate.lower())
            candidate_sorted.sort()
            if candidate_sorted == word_sorted:
                anagram_list.append(candidate)
    
    return anagram_list
