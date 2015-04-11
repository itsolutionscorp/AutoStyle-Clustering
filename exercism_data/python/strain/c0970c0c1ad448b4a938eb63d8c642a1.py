def keep(sequence, test_fun):
    return [x for x in sequence if test_fun(x)]

def discard(sequence, test_fun):
    return [x for x in sequence if not test_fun(x)]
