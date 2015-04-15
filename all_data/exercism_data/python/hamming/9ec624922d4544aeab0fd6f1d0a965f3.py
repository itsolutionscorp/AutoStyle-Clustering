def distance(a,b):
    mutations = [ i!=j for i,j in zip(a,b)]
    return sum(mutations)
