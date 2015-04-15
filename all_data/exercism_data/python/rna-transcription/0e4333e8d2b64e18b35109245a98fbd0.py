import re

'''
    Converts DNA to RNA (G-C,C-G,T-A,A-U)
    References used:
    http://code.activestate.com/recipes/81330-single-pass-multiple-replace/
    http://stackoverflow.com/questions/15175142/how-can-i-do-multiple-substitutions-using-regex-in-python
'''

dict_of_RNA = {
    "C": "G",
    "G": "C",
    "T": "A",
    "A": "U"
}

def to_rna(text):
    global dict_of_RNA

    # if text is empty or contains only spaces, return statement
    if not text.strip():
        return "String is empty."

    # Create a regular expression from the dictionary keys
    # re.compile, compile an expression into an expression object
    # |, the or operator checks for any of the key matches.
    regex = re.compile("(%s)" % "|".join(dict_of_RNA.keys()))

    # For each match, look-up corresponding value in dictionary
    # lambda, looks for pattern match
    # text, replace the dict key with dict value
    return regex.sub(lambda mo: dict_of_RNA[mo.group()], text)
