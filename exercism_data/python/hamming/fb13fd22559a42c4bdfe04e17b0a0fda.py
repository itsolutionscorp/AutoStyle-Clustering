def hamming(a_string, o_string):
    diff = abs(len(a_string) - len(o_string))

    for a, b in zip(a_string, o_string):
        if a != b:
            diff += 1
    return diff
            
