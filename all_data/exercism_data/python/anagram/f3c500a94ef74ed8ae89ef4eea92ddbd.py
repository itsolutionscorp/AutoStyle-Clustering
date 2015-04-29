def simplify_word(word):
    word = sorted(word.lower())
    return word


def detect_anagrams(word, grams):
    anagrams = []
    simple_word = simplify_word(word)
    for gram in grams:
        if simple_word == simplify_word(gram) and gram.lower() != word.lower():
            anagrams.append(gram)
    return anagrams
