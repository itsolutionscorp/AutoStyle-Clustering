def to_rna(text):
    import re
    if not isinstance(text,str) or \
	   not re.match('[A-Z]', text):
         return 
    rna_map = {'G' : 'C',
	           'C' : 'G',
               'T' : 'A',
	           'A' : 'U'}
    if len(text) > 1:
       results = []
       for character in list(text):
           results.append(rna_map[character])
       return "".join(results)
    else:
       return rna_map[text]
