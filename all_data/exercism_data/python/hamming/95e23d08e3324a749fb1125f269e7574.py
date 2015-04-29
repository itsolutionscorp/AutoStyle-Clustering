def distance(stran1, stran2):
    assert len(stran1) == len(stran2)
    return sum(ch1 != ch2 for ch1, ch2 in zip(stran1, stran2))

