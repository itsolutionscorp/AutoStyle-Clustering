def to_rna(nucleotide_string):
    """
    Translate a DNA nucleotide sequence into RNA
    """
    return "".join([_to_rna(dna) for dna in nucleotide_string])

def _to_rna(single_nucleotide):
    """
    Translate a single DNA nucleotide into RNA
    """
    # Translation dictionary
    _t = {
            'G': 'C',
            'C': 'G',
            'T': 'A',
            'A': 'U'
        }
    if not single_nucleotide in _t:
        raise Exception('Invalid nucleotide specified: %s' % single_nucleotide)
    try:
        return _t.get(single_nucleotide)
    except:
        # We're catching all invalid conditions here
        return " "
