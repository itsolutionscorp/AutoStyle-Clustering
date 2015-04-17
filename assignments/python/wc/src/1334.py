def word_count(phrase):
    counter = {}
    for s in phrase.split():
            counter[s] = counter.get(s,0)+1
    return counter
