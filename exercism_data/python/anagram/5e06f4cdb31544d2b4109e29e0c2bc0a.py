def detect_anagrams(match_word, words):
    match_word_list = sorted(match_word.lower())

    results = []

    for word in words:
        word_list = sorted(word.lower())

        if word.lower() != match_word and word_list == match_word_list:
            results.append(word)

    return results
