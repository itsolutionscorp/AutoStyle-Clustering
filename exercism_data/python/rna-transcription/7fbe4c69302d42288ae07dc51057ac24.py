from timeit import Timer


def to_rna(strand):
    conversion = {
        'G': 'C',
        'C': 'G',
        'T': 'A',
        'A': 'U'
    }
    return ''.join((conversion[x] for x in strand))


def to_rna_orig(strand):
    conversion = {
        'G': 'C',
        'C': 'G',
        'T': 'A',
        'A': 'U'
    }
    rna = ''
    for nuc in strand:
        rna += conversion[nuc]
    return rna


if __name__ == "__main__":
    timer = Timer("res = to_rna('GGGCCTAACCTT')",
                  "from __main__ import to_rna")
    print("Optimised List Comprehension:", timer.timeit(number=10000))

    timer_orig = Timer("res = to_rna_orig('GGGCCTAACCTT')",
                       "from __main__ import to_rna_orig")
    print("Original Version:", timer_orig.timeit(number=10000))
