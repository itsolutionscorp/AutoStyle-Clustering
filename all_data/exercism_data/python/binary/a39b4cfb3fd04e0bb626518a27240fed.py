def is_binary(value):
    number_string = list(str(value))
    for elm in number_string:
        if elm != '0' and elm != '1':
            return False
    return True


def convert_binary(value):
    number_string = list(str(value))
    number_string.reverse()
    return_value = 0
    counter = 0
    for elm in number_string:
        if elm == '1':
            return_value += 2**counter
        counter += 1
    return return_value


def parse_binary(value):
    if not is_binary(value):
        raise ValueError
    else:
        return convert_binary(value)
