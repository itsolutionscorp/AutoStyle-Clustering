def to_rna(dna):
    rna_list=[]
    if type(dna) != type("I don't know how to write a data value of type"):
        return "Invalid DNA strand! Red Blood Cells, ATTACK!!!"
    for nucleotide in dna:
        if nucleotide == "G" or nucleotide == "g":
            rna_list.append("C")
        elif nucleotide == "C" or nucleotide == "c":
            rna_list.append("G")
        elif nucleotide == "T" or nucleotide == "t":
            rna_list.append("A")
        elif nucleotide == "A" or nucleotide == "a":
            rna_list.append("U")
        elif nucleotide == "U" or nucleotide == "u":
            return "Hey, this is RNA. Hemoglobin, ATTACK!!!"
        else:
            return "Invalid DNA detected.  White Blood Cells, ATTACK!!!"
    return "".join(rna_list)

# That was very fun to write!
