def distance(a,b):
    return len([val for i,val in enumerate(a) if not (b[i]==val)])
