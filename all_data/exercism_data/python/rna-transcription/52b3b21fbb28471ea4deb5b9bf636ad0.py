def to_rna(nucleotides):
    DNA_to_RNA_dict = {'G':'C',
                        'C':'G',
                        'T':'A',
                        'A':'U'}

    RNA = [DNA_to_RNA_dict[n] for n in nucleotides]
    return ''.join(RNA)
