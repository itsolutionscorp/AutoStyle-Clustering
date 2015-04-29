def to_rna(sequence):
    if len(sequence) == 0:
        return ""
    if len(sequence) > 1:
        head, tail = sequence[0], sequence[1:]
    else:
        head = sequence
        tail = ""

    if head == 'G':
        return "C" + to_rna(tail)
    if head == 'C':
        return "G" + to_rna(tail)
    if head == 'T':
        return "A" + to_rna(tail)
    # if head == 'A':
    return "U" + to_rna(tail)
