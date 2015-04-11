
def to_rna(input):

    list1 = []
    
    for char in input:
        if(char == 'C'):
            list1.append('G')
            continue
        if(char == 'G'):
            list1.append('C')
            continue
        if(char == 'T'):
            list1.append('A')
            continue
        if(char == 'A'):
            list1.append('U')
            continue
    return "".join(list1)       #joins list into a single string

#print to_rna('ACGTGGTCTTAA')
