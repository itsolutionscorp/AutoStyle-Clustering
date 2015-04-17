"""
Created on Thu Sep 25 16:29:03 2014
"""
def slices(input_string, length_of_slice):
    string_len = len(input_string)
    if string_len < length_of_slice:
        raise ValueError
    elif length_of_slice < 1:
        raise ValueError
    else:
        value_list = []
        for i in range(string_len-length_of_slice+1):
            input_slice = map(int, list(input_string[i:length_of_slice+i]))
            if input_slice not in value_list:
                value_list.append(input_slice)
    return value_list
