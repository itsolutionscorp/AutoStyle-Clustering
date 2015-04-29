to_rna = lambda s: "".join(map({'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}.get, s))
