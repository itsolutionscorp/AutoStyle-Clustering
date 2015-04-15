def slices(sequence, count):
    if count==0 or count>len(sequence):
        raise ValueError

    return [list(map(int,sequence[x:count+x])) for x in range(len(sequence)-count+1)]
