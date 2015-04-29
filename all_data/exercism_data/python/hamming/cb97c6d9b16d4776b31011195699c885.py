###
### Hamming distance python exercise.
###

def distance(sequence1, sequence2):
    
    ###If not the same length, Hamming Distance 
    ###Cannot be calculated. Return an error.

    if len(sequence1) == len(sequence2):

        counter = 0
        hamming = 0

        for i in sequence1:

            ###Compare characters at same
            ###Position in the strings.
            ###Increment Hamming distance
            ###for each difference.

            if sequence1[counter] != sequence2[counter]:

                hamming = hamming + 1

            counter = counter + 1

        return hamming

    else:

        return None
             
    
    
