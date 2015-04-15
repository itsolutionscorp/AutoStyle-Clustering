def to_rna(sequence):
    new_sequence = sequence
    new_sequence = new_sequence.replace("A", "U")  # must be before T->A rule
    new_sequence = new_sequence.replace("T", "A")  # must be after A->U rule
    new_sequence = new_sequence.replace("G", "X")  # G/C swap. X as swap var
    new_sequence = new_sequence.replace("C", "G")  # Now safe to process C->G
    new_sequence = new_sequence.replace("X", "C")  # G->C from swap state
    return new_sequence
