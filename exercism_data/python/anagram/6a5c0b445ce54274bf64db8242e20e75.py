def detect_anagrams(inword, inlist):
    outlist = []
    for word in inlist:
        if inword.lower()!=word.lower() and ''.join(sorted(inword.lower()))==''.join(sorted(word.lower())):
            outlist.append(word)
    return outlist
