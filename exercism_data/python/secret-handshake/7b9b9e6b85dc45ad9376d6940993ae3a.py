def handshake(value):
    shake_map = {
        0 : 'wink',
        1 : 'double blink',
        2 : 'close your eyes',
        3 : 'jump',
    }
    int_value = int(value)
    return_value = []
    if is_binary(value):
        for elm in shake_map.keys():
            if has_element(elm, value):
                if is_reversed(int_value):
                    return_value = [shake_map[elm]] + return_value
                else:
                    return_value.append(shake_map[elm])
    elif value < 0 or value > 32:
        return []
    else:
        value = "{0:b}".format(int_value)
        for elm in shake_map.keys():
            if has_element(elm, value):
                if is_reversed(int_value):
                    return_value = [shake_map[elm]] + return_value
                else:
                    return_value.append(shake_map[elm])

    return return_value


def is_binary(value):
    number_string = list(str(value))
    for elm in number_string:
        if elm != '0' and elm != '1':
            return False
    return True


def has_element(elm, value):
    number_string = list(str(value))
    number_string.reverse()
    try:
        if(number_string[elm]) == '1':
            return True
        else:
            return False
    except:
        return False


def is_reversed(int_value):
    reverse = False
    if is_binary(int_value):
        if (int_value - 10000) > 0:
            reverse = True
    else:
        if (int_value - 15) > 0:
            reverse = True
    return reverse


def code(value):
    code_map = {
        'wink' : 1,
        'double blink' : 10,
        'close your eyes' : 100,
        'jump' : 1000,
        'reverse_value' : 10000
    }
    try:
        new_value = 0
        for elm in code_map.keys():
            if elm in value:
                new_value +=code_map[elm]

        prev = 0
        to_reverse = False
        for curr in value:
            if code_map[curr] < prev:
                to_reverse = True
            prev = code_map[curr]
        if to_reverse:
            new_value += code_map['reverse_value']
        return str(new_value)
    except:
        return '0'
