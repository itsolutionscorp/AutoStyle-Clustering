def detect_anagrams(inword, inlist):
    outlist= [word for word in inlist 
        if inword.lower()!=word.lower() and sorted(inword.lower())== sorted(word.lower())]
    return outlist
