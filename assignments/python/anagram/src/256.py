def detect_anagrams(word, anagram):
    if word in anagram:
        anagram.remove(word)
    title, ret, word = False, [], ''.join(sorted(list(word)))
    for selected in anagram:
        if word.istitle():
            selected.lower()
            title = True
        if ''.join(sorted(list(selected))) == word:
            if title:
                selected.title()
            ret.append(selected)
    return ret
