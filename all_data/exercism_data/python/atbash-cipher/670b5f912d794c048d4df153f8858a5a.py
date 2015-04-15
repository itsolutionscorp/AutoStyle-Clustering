import re

def decode(str):
    temp_result = ''.join(str.split())
    result = ""
    for char in temp_result:
        result += convert(char)
    return result

def encode(str):
    test_str = ''.join(re.split('[.,!\s]', str.lower()))
    result = ""

    for i in range(len(test_str)):
        if i > 1 and i % 5 == 0:
            result += " "
        if test_str[i].isalpha():
            result += convert(test_str[i])
        else:
            result += test_str[i]

    return result

def convert(temp_char):
    convert_char = ord(temp_char)

    if convert_char >= 110:
        convert_char = chr(110 - (convert_char + 1 - 110))
    else:
        convert_char = chr(110 + (110 - convert_char - 1))

    return convert_char
