def tails(strands):
    return [strands[0][1:],strands[1][1:]]

def one_is_used_up(strands):
    return (strands[0] == '' or strands[1] == '')

def different_first_nucleobase(strands):
    return (strands[0][0] != strands[1][0])

def calc(distance, strands):
    if one_is_used_up(strands):
        return distance
    if different_first_nucleobase(strands):
        distance += 1
    return calc(distance,tails(strands))

def fill(strands):
    shortest = min(len(strands[0]),len(strands[1]))
    longest = max(len(strands[0]),len(strands[1]))
    if len(strands[0]) < len(strands[1]):
        strands[0] = strands[0].ljust(longest,'*')
    if len(strands[0]) > len(strands[1]):
        strands[1] = strands[1].ljust(longest,'*')
    return [strands[0],strands[1]]

def hamming(strand1, strand2):
    distance = 0
    return calc(distance,fill([strand1,strand2]))
