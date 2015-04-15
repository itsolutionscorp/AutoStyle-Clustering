def detect_anagrams(word, candidate_list):
    lowercase_word = word.lower()
    sorted_word = sorted(lowercase_word)
    
    def predicate(candidate):
        lowercase_candidate = candidate.lower()
        return (lowercase_candidate != lowercase_word 
                and sorted(lowercase_candidate) == sorted_word)

    return list(filter(predicate, candidate_list))
