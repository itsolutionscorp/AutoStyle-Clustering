def word_count(s):
    out = {}

    for word in s.split():
        word = filter(str.isalnum, word.lower())
        if not word:
            continue
        
        if not word in out:
            out[word] = 1
        else:
            out[word] += 1

    return out
