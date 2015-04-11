from operator import mul

def largest_product(sequence, length):
    
    if length == 0:
        return 1
    else:
        return max(find_product(x) for x in slices(sequence, length))
        

def find_product(elements):
    return reduce (mul, elements)
    
   
def slices(sequence, length):
    
    if len(sequence) < length or length == 0:
        raise ValueError
        
    else:
    
        bucket_of_slices = []
        new_seq = list(sequence)
        
        seq_count = len(sequence)
        slice_count = 0
        
        while seq_count >= length:
            cut = new_seq[slice_count : (slice_count + length)]
            cut = [int(i) for i in cut]
            bucket_of_slices.append(cut)
            slice_count += 1
            seq_count -= 1
           
    return bucket_of_slices
