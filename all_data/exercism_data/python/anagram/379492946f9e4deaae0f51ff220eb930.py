def detect_anagrams(word, possibles):
    anagrams = []


    for candidate in possibles:
        if candidate.lower() == word.lower():
            pass
        elif sorted(word.lower()) == sorted(candidate.lower()):
            anagrams.append(candidate)

    return anagrams
