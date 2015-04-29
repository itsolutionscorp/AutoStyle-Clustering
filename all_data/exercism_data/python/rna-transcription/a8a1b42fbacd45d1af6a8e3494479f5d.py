_mapping = {
    "G": "C",
    "C": "G",
    "T": "A",
    "A": "U"
  }


def to_rna(seq):
    return "".join([_mapping[c] for c in seq])
