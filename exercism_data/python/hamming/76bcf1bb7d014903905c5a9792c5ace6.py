def distance(DNA1,DNA2):
    return sum([1 if c != b else 0 for c,b in zip(DNA1,DNA2)])
