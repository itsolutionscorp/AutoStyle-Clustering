# Iterate over the shortest strand to find mutations
def hamming(nuc1,nuc2):
    # Set number of mutations immediatly if strand lengths are unequal
    mutations = len(nuc1)-len(nuc2)
    
    # Select the shortest strand.  If mutation is negative use absolute value
    if mutations >= 0:
        shortest_nuc = nuc2
    else:
        shortest_nuc = nuc1
        mutations = abs(mutations)
    
    # Iterate over shortest strand then increment mutations when found
    for i in range(len(shortest_nuc)):
        if not nuc1[i] == nuc2[i]:
            mutations += 1
            
    return mutations
        
