def deconstruct(word):
    dict = {}
    for c in word:
        if c in dict.keys():
            dict[c] += 1
        else:
            dict[c] = 1
    return dict

def detect_anagrams(word, wlist):
    word = word.lower()
    w = deconstruct(word)

    matches = []
    for i in wlist:
        i2 = i.lower()
        if i2 == word:
            continue

        wi = deconstruct(i2)
        if w == wi:
            matches.append(i)

    return matches
