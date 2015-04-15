def detect_anagrams(word, candidates):
    word_analysis = analyze(word)
    return [c for c in candidates if c.lower() != word.lower() and analyze(c) == word_analysis]


def analyze(word):
    return sorted(letter for letter in word.lower())
