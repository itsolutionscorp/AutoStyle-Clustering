def word_count(words):
    wordl = words.split()
    wordlist = []
    num = []
    for x in wordl:
        if x not in wordlist:
            wordlist.append(x)
    for it in wordlist:
        num.append(wordl.count(it))
    return {wordlist[x]: num[x] for x in range(len(wordlist))}
