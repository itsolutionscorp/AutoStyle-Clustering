def detect_anagrams(ana, list):
    newlist = []
    for x in list:
        if sorted(x.lower()) == sorted(ana.lower()) and not (x.lower() == ana.lower()):
            newlist.append(x)
    return newlist
