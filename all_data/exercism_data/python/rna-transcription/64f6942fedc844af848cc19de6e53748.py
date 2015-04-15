#didnt work out with replace method
#so i used the translate method with a map
#Is translate deprecated in Python3.4?
def to_rna(what):
    
    mapper = what.maketrans('GCTA','CGAU')
    what = what.translate(mapper)
    
    return what
