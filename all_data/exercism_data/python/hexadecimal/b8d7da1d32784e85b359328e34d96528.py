def hexa(string):

    # Set up the mapping that we'll use
    hex_chars = "0123456789abcdef"
    hex_mapping = dict(zip(hex_chars, range(len(hex_chars))))

    try:
        # Set up an iterator
        place_enumerator = enumerate(hex_mapping[char]
                                     for char in reversed(string.lower()))

        # Calculate the value for each place and sum them all
        return sum(place * (16 ** exponent)
                   for exponent, place in place_enumerator)

    # Catch if there's a character that isn't in the dictionary
    except KeyError:
        raise ValueError("""Invalid string {}: hex strings must contain
            only digits 0-9 and letters a-f""".format(string))
