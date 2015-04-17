def slices(sequence, chunk):
    all_slices = []
    if chunk == 0:
        raise ValueError
    elif chunk <= len(sequence):
        for i in range(0,len(sequence)-chunk+1):
            new_seq = sequence[i:i+chunk]
            all_slices.append([int(i) for i in str(new_seq)])
        return all_slices
    else:
        raise ValueError
