def distance(DNA1,DNA2):
    return sum(c != b for c,b in zip(DNA1,DNA2))
