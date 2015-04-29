def slices(input_string, slice_size):
    if slice_size > len(input_string) or slice_size < 1:
        raise ValueError 

    int_list = [int(char) for char in input_string]

    result_list = []
    for index in range(len(int_list)-(slice_size - 1)):
        result_list.append(int_list[index:index+slice_size])
    return result_list
