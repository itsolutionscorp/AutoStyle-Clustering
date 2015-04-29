def to_rna(input_str):
    conversion = {'G':'C', 'C':'G','T':'A','A':'U'}

    result = ''
    for char in input_str:
        if char in conversion:
            result += conversion[char]
        else:
            raise KeyError

    return result
