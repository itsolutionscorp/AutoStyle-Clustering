def get_letterlist(word):
    return sorted([letter for letter in word.lower()])

def detect_anagrams(word, pos_anagrams):
    anagrams = []
    for pos_anagram in pos_anagrams:
        if word.lower() != pos_anagram.lower() and get_letterlist(word) == get_letterlist(pos_anagram):
            anagrams.append(pos_anagram)
    return anagrams
