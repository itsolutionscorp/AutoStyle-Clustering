def word_count(words):
    alps = "abcdefghijklmnopqrstuvwxyz"
    alps = alps + alps.upper()
    alp_set = set(alps)
    word_set = set(words)
    nonalps = alp_set.difference(word_set)
    for ch in nonalps:
        words = words.replace(ch,"")
    words = words.strip()
    word_list = words.split()
    word_set = set(word_list)
    retdict = {}
    for word in word_set:
        retdict[word] = word_list.count(word)
    return retdict
