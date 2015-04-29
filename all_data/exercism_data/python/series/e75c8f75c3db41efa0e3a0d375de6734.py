def slices(string, number):
    # Logic of the program
    # try:
    #     empty_list = []
    #     while len(string) >= number:
    #         temp_list = int(string[0:number])
    #         empty_list.append(temp_list)
    #         string = string[1:]
    #     return empty_list
    # except ValueError:
    #     print (str(number) + " is not a valid length argument.")

    try:
        empty_list = []
        while len(string) >= number:
            temp_list = []
            temp_string = string[:number]
            for char in temp_string:
                if char == '0':
                    temp_list.append(0)
                else:
                    i = int(char)
                    temp_list.append(i)
            empty_list.append(temp_list)
            string = string[1:]
        return empty_list
    except ValueError:
        raise ValueError(str(number) + " is not a valid length argument.")
