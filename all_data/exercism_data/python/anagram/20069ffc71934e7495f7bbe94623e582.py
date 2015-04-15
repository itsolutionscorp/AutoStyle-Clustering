def detect_anagrams(target_word, words):
    target_word = target_word.lower()
    res = list()
    for o_word in words:
        word = o_word.lower()
        if len(word) != len(target_word) or word == target_word:
            continue
        found = True
        for char in set(word):
            if target_word.count(char) != word.count(char):
                found = False
                break
        if found:
            res.append(o_word)
    return res
