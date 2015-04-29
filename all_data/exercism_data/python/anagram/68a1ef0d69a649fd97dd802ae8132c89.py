def is_anagram(word, candidate):
    w = word.lower() 
    c = candidate.lower()
    if w != c:
        return sorted(w) == sorted(c)


def detect_anagrams(word, candidate_list):
    return [i for i in candidate_list if is_anagram(word, i)]


	
