def to_rna(sequence):
    sequence = sequence.replace('A','U')
    sequence = sequence.replace('T','A')
    sequence = sequence.replace('G','*')
    sequence = sequence.replace('C','G')
    sequence = sequence.replace('*','C')
    return sequence
