def to_rna(string):
    output=""
    for i in string:
        if i=="C":
            output+="G"
        if i=="G":
            output+="C"
        if i=="T":
            output+="A"
        if i=="A":
            output+="U"
    
    return output
