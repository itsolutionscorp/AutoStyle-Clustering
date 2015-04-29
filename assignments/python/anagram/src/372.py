def detect_anagrams(word, candidates):
    clist = [''.join(sorted(x.lower())) for x in candidates]
    sorted_word = ''.join(sorted(word.lower()))
    if sorted_word not in clist:
        return []
    else:
        idxs = []
        for i, val in enumerate(clist):
            if val == sorted_word and candidates[i].lower() != word.lower():
                idxs.append(i)
        matches = []
        for x in idxs:
            matches.append(candidates[x])
        return matches
