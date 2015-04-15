def detect_anagrams(word, option_list):
    result = []

    for w in option_list:
        if len(w) != len(word) or w.lower() == word.lower():
            continue
        if sorted(list(w.lower()), key=str.lower) == sorted(list(word.lower()), key=str.lower):
            result.append(w)
    return result
