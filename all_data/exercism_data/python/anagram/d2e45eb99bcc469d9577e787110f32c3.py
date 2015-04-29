def detect_anagrams(word, args):
    anagrams =[]
    word =word.lower()
    for item in args:
        if sorted(word) == sorted(item.lower()) and word.lower() != item.lower():
            anagrams.append(item)

    return anagrams
