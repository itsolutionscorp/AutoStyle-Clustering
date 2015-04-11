''' Module for DNA->RNA transcription
    Lessons: use of list comprehensions vs generator expressions, use of dict()
    and zip() for dictionary creation, use of translate and maketrans from 
    string module.
'''

from string import translate, maketrans

def to_rna(dna):
    # return_str = ''
    # i = 0
    # for base in dna:
    #     if base == 'C':
    #         return_str = return_str + 'G'
    #         i = i + 1
    #     elif base == 'G':
    #         return_str = return_str + 'C'
    #         i = i + 1
    #     elif base == 'A':
    #         return_str = return_str + 'U'
    #         i = i + 1
    #     elif base == 'T':
    #         return_str = return_str + 'A'
    #         i = i + 1
    #     else:
    #         return 'Invalid base at ' + (i + 1)
    # return return_str

    # This implementation takes advantage of generator expressions
    # return "".join(dict(zip('ATCG', 'UAGC'))[base] for base in dna)
    
    # This implementation uses maketrans and tranlate functions from the
    # string standard library. In python 3, maketrans is available as a method 
    # for static string so no need to import from string if using python 3.
    return translate(dna, maketrans('ATCG', 'UAGC'))
