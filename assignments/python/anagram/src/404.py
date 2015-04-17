def detect_anagrams(string, ls):
    target = ''.join(sorted(string.lower()))
    anagrams = []
    for word in ls:
        if string.lower() == word.lower():
            continue
        elif len(word) != len(string):
            continue
        elif ''.join(sorted(word.lower())) == target:
            anagrams.append(word)
    return anagrams
