def detect_anagrams(anagram, alist):
    """
    (str, list of str)-> list of str
    """
    result = []
    sanagram = list(anagram.lower())
    sanagram.sort()
    for word in alist:
        #discard an entry if it is the exact word
        if anagram.lower() == word.lower():
            continue
        sword = list(word.lower())
        sword.sort()
        if sanagram == sword:
            result.append(word)
    return result
