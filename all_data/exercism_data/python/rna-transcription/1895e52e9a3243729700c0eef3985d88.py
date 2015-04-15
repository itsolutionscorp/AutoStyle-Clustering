def to_rna(convert_str):

    replaceValues = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
    newStr = ""

    # Iterate through each character in string and replace when needed
    for char in convert_str:
        newStr += replaceValues[char]

    return newStr
