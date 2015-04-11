def letter_count(word):
    counts = {}
    for letter in word:
        if letter not in counts:
            counts[letter] = 0
        counts[letter] += 1
    return counts

def detect_anagrams(word, maybe_anagrams):
    word = word.lower()
    letters = letter_count(word)
    anagrams = []
    for anagram in maybe_anagrams:
        orig = anagram
        anagram = anagram.lower()
        if word != anagram and letters == letter_count(anagram):
            anagrams.append(orig)
    return anagrams
