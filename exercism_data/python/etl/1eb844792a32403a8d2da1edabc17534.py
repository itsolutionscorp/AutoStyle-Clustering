
def transform(old):
    new = {}
    for score, letter in old.items():
        for x in letter:
            new[x.lower()] = score
    return new
   
