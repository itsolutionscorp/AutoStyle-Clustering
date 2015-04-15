def transform(old):
    new = {}
    
    for score in old.keys():
        for word in old[score]:
            new[word.lower()] = score
    
    return new
