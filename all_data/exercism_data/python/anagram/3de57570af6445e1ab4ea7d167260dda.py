def detect_anagrams(word, word_list):
    result = []
    for w in word_list:
        if word.lower() != w.lower() and sorted(word.lower()) == sorted(w.lower()):
            result.append(w)

    return result
