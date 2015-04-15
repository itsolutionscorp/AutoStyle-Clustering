def to_rna(dna_string):
    rna_letters = []
    
    #iterate over every letter in the dna_string
    #append the rna letter to rna_letters
    for letter in dna_string:
        if letter == 'G': rna_letters.append('C')
        elif letter == 'C': rna_letters.append('G')
        elif letter == 'T': rna_letters.append('A')
        elif letter == 'A': rna_letters.append('U')
        else: rna_letters.append(letter)
        
    #use join() to recombine the letters into a string
    return ''.join(rna_letters)
