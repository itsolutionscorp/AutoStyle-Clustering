def word_count(phrase):
    things = phrase.split()
    returned = {}
    for k in things:
        if k in returned:
            returned[k] += 1
        else:
            returned[k] = 1
    return returned
