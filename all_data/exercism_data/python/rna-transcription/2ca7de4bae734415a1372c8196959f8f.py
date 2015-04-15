_rna_map = {"G": "C", "C": "G", "T": "A", "A": "U", "U": "A"}

def to_rna(phrase):
    return "".join([_rna_map.get(letter,letter) for letter in phrase])
