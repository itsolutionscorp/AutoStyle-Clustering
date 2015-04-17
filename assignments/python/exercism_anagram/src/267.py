def detect_anagrams(word, list):
    final = []
    count = {}
    for s in word.lower():
        if count.has_key(s):
            count[s] += 1
        else:
            count[s] = 1
    for w in list:
        if w.lower() != word:
            wcount = {}
            for s in w.lower():
                if wcount.has_key(s):
                    wcount[s] += 1
                else:
                    wcount[s] = 1
            if wcount == count:
                final.append(w)
    
    return final
