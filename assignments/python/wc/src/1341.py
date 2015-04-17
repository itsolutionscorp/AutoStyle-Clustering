from string import letters, digits
def word_count(phrase):
    results = {}
    for w in phrase.split(' '):
        w = ''.join(x for x in w if x in letters + digits).lower()
        if not w:
            continue
        results[w] = results.get(w, 0) + 1
    return results
