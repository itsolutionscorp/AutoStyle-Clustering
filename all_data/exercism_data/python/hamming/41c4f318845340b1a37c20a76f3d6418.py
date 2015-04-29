def tails(strands):
    return [strands[0][1:],strands[1][1:]]

def one_is_used_up(strands):
    return (strands[0] == '' or strands[1] == '')

def different_first_nucleobase(strands):
    return (strands[0][0] != strands[1][0])

def calc(distance, strands):
    if one_is_used_up(strands): return distance
    if different_first_nucleobase(strands): distance += 1
    return calc(distance,tails(strands))

def hamming(strand1, strand2):
    difference = abs(len(strand1) - len(strand2))
    return calc(0,[strand1,strand2]) + difference
