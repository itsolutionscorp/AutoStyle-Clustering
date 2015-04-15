def distance(fst, snd):
    return [i != j for i, j in zip(fst, snd)].count(True)
