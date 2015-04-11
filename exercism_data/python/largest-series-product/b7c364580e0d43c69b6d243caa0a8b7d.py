def largest_product(number,slice):
    nums = slices(number,slice)
    maxtotal = 0
    for a in nums:
        total = 1
        for b in a:
            total *= b
        if total > maxtotal:
            maxtotal = total
    return maxtotal
def slices(longnum, size):
    if size > len(longnum):
        raise ValueError
    slicelist = []
    for a in range (len(longnum)-size+1):
        slicelist.append(list(longnum[a:a+size]))
    return [[int(b) for b in c] for c in slicelist]
