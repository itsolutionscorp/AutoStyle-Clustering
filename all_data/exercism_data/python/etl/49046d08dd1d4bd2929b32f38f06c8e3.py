def transform(input_dict):
    """
    transform(dict) -> dict

    Creates a transposed dict. The new dict has a key k with value v for each
    value k in input_dict[v] for each key v in input_dict.
    """

    new_dict = dict()
    for key, values in input_dict.items():
        for value in map(str.lower, values):
            new_dict[value] = key

    return new_dict
