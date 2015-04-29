def slices(string, number):
    if number <= 0 or len(string) < number:
        raise ValueError(str(number) + " is not a valid length argument.")
    slices_list = []
    temp_list = [int(char) for char in list(string)]
    while len(temp_list) >= number:
        slices_list.append(temp_list[:number])
        temp_list = temp_list[1:]
    return slices_list
