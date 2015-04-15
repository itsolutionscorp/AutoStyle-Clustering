def to_rna(phrase):
    return "".join([convert(letter) for letter in phrase])


def convert(letter):
    return {"G": "C", "C": "G", "T": "A", "A": "U", "U": "A"}[letter]
