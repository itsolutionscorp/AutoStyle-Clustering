def sieve(limit):
    test_range = list(range(2,limit+1))
    results = []
    marked = set()
    for x in test_range:
        if x not in marked:
            for y in test_range[test_range.index(x)+1:]:
                
                if y % x == 0:
                    
                    marked.add(y)
            results.append(x)

    return results
