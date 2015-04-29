def to_rna(string):
    char_array = list(string)
    dict = {"G": "C",
            "C": "G",
            "T": "A",
            "A": "U"}
    for i, c in enumerate(char_array):
        char_array[i] = dict[c]
    return "".join(char_array)
