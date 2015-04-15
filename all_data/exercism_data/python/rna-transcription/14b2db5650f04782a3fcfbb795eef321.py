def to_rna(DNAstring):          # Take in a DNA string
    
    dna = list(DNAstring)       # Create a list of the DNA string characters
    rna = []                    # Initialize empty list for RNA

    for element in dna:         # Take the elements in the DNA list, and 
        if element == "G":      # convert them to their corresponding RNA
            rna.append("C")     # and append those elements to the RNA list
        elif element == "C":
            rna.append("G")
        elif element == "T":
            rna.append("A")
        elif element == "A":
            rna.append("U")
    return "".join(rna)         # Join the RNA list into a single string
