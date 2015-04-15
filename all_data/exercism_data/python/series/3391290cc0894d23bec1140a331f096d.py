def slices(seq1, length):
    
    if len(seq1) < length or length == 0:
        raise ValueError
        
    else:
    
        bucket = []
        seq2 = list(seq1)
        
        seq_count = len(seq1)
        slice_count = 0
        
        while seq_count >= length:
            cut = seq2[slice_count : (slice_count + length)]
            cut_int = [int(i) for i in cut]
            bucket.append(cut_int)
            slice_count += 1
            seq_count -= 1
           
    return bucket
