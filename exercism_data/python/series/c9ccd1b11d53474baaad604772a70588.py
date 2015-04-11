
def slices (sequence,slic):
    if slic > len(sequence) or slic < 1:
        raise ValueError
    sequence = [int(x) for x in sequence]
    slices = []
    for x in range(len(sequence)-(slic-1)):
        slices.append(sequence[x:x+slic])
    return slices
        
