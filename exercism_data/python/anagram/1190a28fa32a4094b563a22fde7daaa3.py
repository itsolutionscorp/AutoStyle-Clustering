def detect_anagrams(word, list_of_words):
    anagrams = []
    for w in list_of_words:
        check = (is_anagram(w, word))
        if check:
            anagrams.append(w)

    return anagrams


def is_anagram(w1, w2):
    w1 = w1.lower()
    w2 = w2.lower()

    if w1 == w2 or len(w1) != len(w2):
        return False

    for l in range(len(w1)):
        if w1[0] not in w2:
            return False
        else:
            w2 = w2.replace(w1[0], '', 1)
            w1 = w1[1:]

    return True
