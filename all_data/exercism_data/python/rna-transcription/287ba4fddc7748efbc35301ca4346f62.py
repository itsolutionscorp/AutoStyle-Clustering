def to_rna(string):
    list=[]
    rna_char=''
    for each in string:
        if each=='G':
            rna_char='C'
        elif each=='C':
            rna_char='G'
        elif each=='T':
            rna_char='A'
        elif each=='A':
            rna_char='U'
        else: 
            continue
        list.append(rna_char)
    return ''.join(list)


to_rna('A')
