def to_rna(chain):
   output_chain = ''
   for element in chain:
       output_chain+=convert(element)
   return output_chain

def convert(element):
    if element == 'G':
        return 'C'
    if element == 'C':
        return 'G'
    if element == 'T':
        return 'A'
    if element == 'A':
        return 'U'
