def to_rna(sequence):

    substitutions = {"G":"C","C":"G","T":"A","A":"U"}
    
    return "".join(substitutions[x] for x in sequence)
