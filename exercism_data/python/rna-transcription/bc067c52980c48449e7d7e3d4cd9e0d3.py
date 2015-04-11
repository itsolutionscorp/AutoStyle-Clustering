def to_rna(phrase):
    
    new_list = []                           #creates an empty list

    for i in phrase:
        if i == 'G':
            new_list.extend('C')            #adds the corresponding letters to list
        if i == 'C':
            new_list.extend('G')
        if i == 'T':
            new_list.extend('A')
        if i == 'A':
            new_list.extend('U')

    new_string = "".join(new_list)          #cats the new list into a string

    return new_string
