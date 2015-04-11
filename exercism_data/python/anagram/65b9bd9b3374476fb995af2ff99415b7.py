def detect_anagrams(word_to_match, words):
    anagrams = []
    lower_to_match = word_to_match.lower()
    sorted_lower_to_match = ''.join(sorted(lower_to_match))

    for word in words:
        lower_word = word.lower()
        if lower_to_match == lower_word:
            continue
        elif sorted_lower_to_match == ''.join(sorted(lower_word)):
            anagrams.append(word)
    return anagrams
