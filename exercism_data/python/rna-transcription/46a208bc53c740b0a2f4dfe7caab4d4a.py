def to_rna(dna):
    m_dict = {"G":"C",
              "C":"G",
              "T":"A",
              "A":"U"}
    return ''.join(m_dict[ch] for ch in dna) 
