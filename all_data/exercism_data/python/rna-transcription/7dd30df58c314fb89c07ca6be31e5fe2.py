mappings = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}
to_rna = lambda inp_str: ''.join(mappings[e] for e in inp_str)
