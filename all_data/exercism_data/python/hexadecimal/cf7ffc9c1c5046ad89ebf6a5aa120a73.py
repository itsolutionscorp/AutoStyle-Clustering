def hexa(string):
    hex_chars = "0123456789abcdef"
    string = string.lower()

    # Validation
    if any(char not in hex_chars for char in string):
        raise ValueError("""Invalid string {}: hex strings must contain
            only digits 0-9 and letters a-f""".format(string))

    place_enumerator = enumerate(hex_chars.find(char)
                                 for char in reversed(string))

    return sum(place * (16 ** exponent)
               for exponent, place in place_enumerator)
