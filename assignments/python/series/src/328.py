def slices(sequence,chunk_size):
    position=0
    mini_list=[]
    answer=[]
    if chunk_size<=len(sequence) and chunk_size>0:
        for char in sequence:
            if len(sequence[position:position+chunk_size])==chunk_size:
                mini_seq = sequence[position:position+chunk_size]
                for digit in mini_seq:
                    mini_list.append(int(digit))
                answer.append(mini_list)
                mini_list=[]
                position+=1
        return answer
    else:
        raise ValueError("Oops!  That was no valid number.  Try again...")
