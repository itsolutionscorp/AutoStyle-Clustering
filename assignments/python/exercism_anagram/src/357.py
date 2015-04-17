def detect_anagrams(word, poss_anagrams):
    output = []
    word = word.lower()
    for i in poss_anagrams:
        if sorted(word) == sorted(i.lower()) and word != i.lower():
            output.append(i)
    return output
