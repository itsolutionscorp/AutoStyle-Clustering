HEX_CHARS = "0123456789abcdef"

def hexa(hexadecimal):
    return sum(_value_per_character(hexadecimal))

def _value_per_character(hexadecimal):
    for exponent, char in enumerate(reversed(hexadecimal.lower())):
        try:
            hex_value = HEX_CHARS.index(char)
            yield hex_value * 16**exponent
        except ValueError:
            raise ValueError(
                "Illegal character '%s' in hexadecimal number" % char)
