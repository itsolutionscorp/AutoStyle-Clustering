def detect_anagrams(word, possibles):
    if word == possibles[0]:
        return []
    word = ''.join(sorted(word.lower()))
    anagrams = [possible for possible in possibles if ''.join(sorted(possible.lower())) == word]
    return anagrams
