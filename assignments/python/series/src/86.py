def slices( N, size ):
    if (size == 0) or (len(N) < size):
        raise ValueError('Bad input!')
    N_shifted = []
    N_chars = []
    N_input = list( N )
    for ch in N_input:
        N_chars.append( int( ch ) )
    for ii in range( 0, size ):
        N_shifted.append( N_chars )
        first_char = N_chars[0]
        N_chars = N_chars[1:]
        N_chars.append( first_char )
    slices_list = []
    for n in list(zip( *N_shifted ))[0:len(N)-size+1]:
        slices_list.append( list(n) )
    return slices_list
