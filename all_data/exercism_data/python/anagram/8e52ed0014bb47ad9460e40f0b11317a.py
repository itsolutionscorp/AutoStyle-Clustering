def detect_anagrams(word, candidates):
    word_letters = _count_letters(word)
    anagrams = []
    for candidate in candidates:
        if word_letters == _count_letters(candidate) and \
           word.lower() != candidate.lower():
            anagrams.append(candidate)
    return anagrams

def _count_letters(word):
    letters = {}
    for char in word:
        char = char.lower()
        letters[char] = letters.get(char, 0) + 1
    return letters
