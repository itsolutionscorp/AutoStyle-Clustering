import re

def to_rna(entry):

  
    dic = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}
    regex = '|'.join(dic)
    entry = re.sub(regex, lambda m: dic[m.group()], entry)

    return entry
