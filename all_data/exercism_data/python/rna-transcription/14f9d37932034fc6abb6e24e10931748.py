relation = {
    'G' : 'C',
    'C' : 'G',
    'T' : 'A',
    'A' : 'U'
}

def to_rna(strain):

   ret = ''.join([relation[nucleo] for nucleo in strain if relation.has_key(nucleo)])
   return ret
