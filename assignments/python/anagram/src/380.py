def detect_anagrams(word, candidates):
    answer = list()
    word_lower = word.lower()
    sorted_lower_word = sorted(word_lower)
    for candidate in candidates:
        if sorted(candidate.lower()) == sorted_lower_word and candidate.lower(
                ) != word_lower:
            answer.append(candidate)
    return answer
