def to_rna(DNAstring):          # Take in a DNA string
    
    dna = list(DNAstring)       # Create a list of the DNA string characters
    rna = []                    # Initialize empty list for RNA

    replacementDict = {"G":"C", "C":"G", "T":"A", "A":"U"}
    
    # replacementDict holds a dictionary showing the DNA -> RNA mappings.

    for key, value in replacementDict:  # Loop through the replacementDict,
        if key == item in dna:          # and compare each key to the elements
            rna.append(key)             # in the dna[] list. Replace them according
                                        # to their value, and append that value to the 
                                        # rna[] list. 

    return "".join(rna)                 # Join the rna[] list into a string and return it
