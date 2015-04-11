def detect_anagrams(base_word, words):
    anagrams = []
    lower_base = base_word.lower()
    sorted_lower_base = ''.join(sorted(lower_base))

    for word in words:
        lower_word = word.lower()
        if lower_base == lower_word:
            continue
        elif sorted_lower_base == ''.join(sorted(lower_word)):
            anagrams.append(word)
    return anagrams
