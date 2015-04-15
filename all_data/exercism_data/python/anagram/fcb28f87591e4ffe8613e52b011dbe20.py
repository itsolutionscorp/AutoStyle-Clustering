def detect_anagrams(word, candidates):
    word_sorted = sorted(word.lower())

    anagram_list = [candidate for candidate in candidates \
    if candidate.lower() != word.lower() \
    and sorted(candidate.lower()) == word_sorted]
    
    return anagram_list
