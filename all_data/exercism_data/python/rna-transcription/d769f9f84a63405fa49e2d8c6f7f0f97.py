from string import maketrans

DNA_TO_RNA = maketrans('GCTA', 'CGAU');

def to_rna(strand):
    '''
    Transcribes a DNA strand to its RNA complement
    '''
    return strand.translate(DNA_TO_RNA)
