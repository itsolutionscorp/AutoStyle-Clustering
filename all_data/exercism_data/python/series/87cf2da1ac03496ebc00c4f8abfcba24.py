__author__ = 'Flavio Miranda'


def slices(sequence, num):
    seqlen = len(sequence)
    if num == 0 or num > seqlen:
        raise ValueError('E')
    if num == len(sequence):
        return [[int(i) for i in sequence]]

    return [list([int(j) for j in sequence[i:i + num]]) for i in range(0, len(sequence) - num + 1)]
