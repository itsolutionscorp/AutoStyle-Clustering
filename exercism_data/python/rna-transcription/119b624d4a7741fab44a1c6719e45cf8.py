rna_dict = {
    'G': 'C',
    'C': 'G',
    'T': 'A',
    'A': 'U'
}

def to_rna(user_input):
    new_arr = []
    for i in user_input:
        new_arr.append(rna_dict[i])
    new_arr = ''.join(new_arr)
    return new_arr
