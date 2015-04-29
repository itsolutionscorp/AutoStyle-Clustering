def largest_product( N, size ):
    if size == 0 and len(N) == 0:
        return 1
    largest_prod = 0
    for seq in slices( N, size ):
        my_prod = 1
        for d in seq:
            my_prod = my_prod * d
        if my_prod > largest_prod:
            largest_prod = my_prod
    return largest_prod


#  Reused code!
def slices( N, size ):

    if (size == 0):
        raise ValueError('Can\'t have size of 0!' )
    if (len(N) < size):
        raise ValueError('Can\t have size larger than # digits of N')
  
    N_shifted = []
    N_chars = []

    # convert input to integers
    #
    N_input = list( N )
    for ch in N_input:
        N_chars.append( int( ch ) )

    # Each iteration, move the first character to the back.
    #   Save the result in N_shifted
    #
    for ii in range( 0, size ):
        N_shifted.append( N_chars )
        first_char = N_chars[0]
        N_chars = N_chars[1:]
        N_chars.append( first_char )

    # By taking the i-th element of each element in N_shifted, we'll have
    #   the i-th substring of length 'size'
    #
    slices_list = []
    for n in list(zip( *N_shifted ))[0:len(N)-size+1]:
        slices_list.append( list(n) )
    return slices_list

