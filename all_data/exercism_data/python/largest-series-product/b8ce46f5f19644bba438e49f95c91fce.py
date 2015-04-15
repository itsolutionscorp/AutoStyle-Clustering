
def slices (sequence,slic):
    if slic > len(sequence) or slic < 1:
        raise ValueError
    sequence = [int(x) for x in sequence]
    slices = []
    for x in range(len(sequence)-(slic-1)):
        slices.append(sequence[x:x+slic])
    return slices

def largest_product(sequence,slic):
    if slic > len(sequence):
        raise ValueError
    sequence = [int(x) for x in sequence]
    larprod = 1
    diff = 0
    for x in range(slic):
        larprod = larprod * sequence[x]
    for x in range(len(sequence)-slic):
        diff += sequence[x+slic] - sequence[x]
        if diff > -1:
            newprod = 1
            for y in range(slic):
                newprod =  newprod * sequence[x+y+1]
            if newprod > larprod:
                larprod = newprod
                diff = 0
    return larprod
