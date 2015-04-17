def detect_anagrams(word, possibles):
    found = []
    for possible in possibles:
        if sorted(list(word.lower())) == sorted(list(possible.lower())) and word.lower() != possible.lower():
            found.append(possible)
    return found
