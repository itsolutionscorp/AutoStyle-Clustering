import re

def hamming(a,b):
    # error handling
    assert type(a) == type(b) == str, "a and b must be strings."
    assert re.search(r'^[ACGT]*$',a) and re.search(r'^[ACGT]*$',a), "Improper DNA sequence. Must be formed with nucleotides A, C, G, or T."

    # initialize hamming distance to 0 and iterate through sequences
    hamming_distance = 0
    minlength, maxlength = min(len(a),len(b)), max(len(a),len(b))
    for i in range(minlength):
        if (a[i] != b[i]):
            hamming_distance += 1
    
    # add tail end
    hamming_distance += maxlength - minlength

    # return result
    return hamming_distance
