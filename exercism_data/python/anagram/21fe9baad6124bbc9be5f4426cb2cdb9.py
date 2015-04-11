def is_same(one, two):
    return one.lower() == two.lower()

def detect_anagrams(word, candidates):
    matches = []

    for candidate in candidates:
        if len(candidate) == len(word) and not is_same(candidate, word):
            remaining_chars = list(word.lower())
            is_anagram = True
            
            for char in candidate.lower():
                if char in remaining_chars:
                    remaining_chars.pop(remaining_chars.index(char))
                else:
                    is_anagram = False
                    break

            if is_anagram:
                matches.append(candidate)

    return matches
