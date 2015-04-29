from string import letters, digits


def word_count(phrase):
    results = {}

    for w in phrase.split(' '):
        # eliminates non alphanum symbols, and lower the result, because
        # a capitalized word is the same as the non-capitalized one
        w = ''.join(x for x in w if x in letters + digits).lower()
        if not w:
            # if nothing left, discount the empty string
            continue

        # get the previous result for this word (0 if not found before)
        # and add 1 to it
        results[w] = results.get(w, 0) + 1

    return results
