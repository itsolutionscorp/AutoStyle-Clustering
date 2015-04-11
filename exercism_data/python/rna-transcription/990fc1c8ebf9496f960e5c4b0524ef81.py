def to_rna(convert_str):

    # Dict of values that require replacing
    replaceValues = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}

    # Convert input string to list of characters
    newStr = list(convert_str)

    # Iterate through list of char and populate replace list
    for i, j in enumerate(newStr):
        newStr[i] = replaceValues[convert_str[i]]

    # Return new list as string
    return "".join(newStr)
