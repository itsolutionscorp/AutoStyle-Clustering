def detect_anagrams(word, candidates):
    word = word.lower()
    letters = _to_letters(word)
    matches = []
    for c in candidates:
        if c.lower() == word: continue
        c_letters = _to_letters(c)
        if c_letters == letters:
            matches.append(c)
    return matches

def _to_letters(word):
    letters = {}
    for char in word.lower():
        if letters.has_key(char):
            letters[char] += 1
        else:
            letters[char] = 1
    return letters
