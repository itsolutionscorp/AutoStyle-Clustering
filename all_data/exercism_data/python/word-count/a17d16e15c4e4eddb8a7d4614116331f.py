from string import punctuation, maketrans, translate

rem_punct_tab = maketrans(punctuation, ' '*len(punctuation))

def word_count(str):
    counts = {}
    for w in translate(str, rem_punct_tab).split():
        w = w.lower()
        if counts.has_key(w):
            counts[w] += 1
        else:
            counts[w] = 1
    return counts
