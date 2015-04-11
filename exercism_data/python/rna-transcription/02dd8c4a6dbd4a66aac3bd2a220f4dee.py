def to_rna(strand):

    # Empty list for results
    result = []
    
    # map for dna -> rna translation
    dna_map={'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}

    # for each nucleotide in the strand
    for nucleotide in strand:
        
        # Add translated nucleotide to result
        result += dna_map[nucleotide]
 
    # return result list as a string
    return "".join(result)
