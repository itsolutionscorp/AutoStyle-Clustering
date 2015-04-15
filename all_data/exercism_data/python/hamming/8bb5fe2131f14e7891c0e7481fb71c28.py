def distance(reference_dna, patients_dna):
    hamming_distance = 0
    for ref_nucleotid, pat_nucleotid in zip(reference_dna, patients_dna):
        if ref_nucleotid != pat_nucleotid:
            hamming_distance += 1

    return hamming_distance
