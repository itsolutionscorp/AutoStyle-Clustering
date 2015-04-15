'''
Created on Sep 24, 2014

@author: Adam Smith
'''
def to_rna(code):
    try:
        transdict = str.maketrans("GCTA", "CGAU")
    except AttributeError:
        # Python2 support
        import string
        transdict = string.maketrans("GCTA","CGAU")
    return code.translate(transdict)
