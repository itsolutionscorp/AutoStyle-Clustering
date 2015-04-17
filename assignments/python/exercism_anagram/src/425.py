def detect_anagrams(word, alist):
    wordl = sorted(list(word.lower()))
    anagram = list()
    for w in alist:
        if w.lower() != word.lower():
            wsort = sorted(list(w.lower()))
            if wordl == wsort:
                anagram.append(w)
    return anagram
