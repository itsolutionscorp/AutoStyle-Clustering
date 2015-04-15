###
### rna transcription exercise
###


def to_rna(dna):

    rnalist = []  ###Initialize list

    for char in dna:   ###Loop over list and append
                       ###the right values.

        if char == 'G':
            
            rnalist.append('C')

        elif char == 'C':
            
            rnalist.append('G')

        elif char == 'T':

            rnalist.append('A')

        elif char == 'A':

            rnalist.append('U')

        else:                     ###Error if given wrong value.    
        
            break

    
    rna = ''.join(rnalist)        ###Join the list with no separator
                                  ###to return in correct format.


    return rna
