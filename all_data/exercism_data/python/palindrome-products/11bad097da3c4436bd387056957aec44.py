def largest_palindrome( max_factor, min_factor=0 ):
    all_palindromes = get_all_palindromes( max_factor, min_factor )
    return (max(all_palindromes),all_palindromes[max(all_palindromes)])

def smallest_palindrome( max_factor, min_factor=0 ):
    all_palindromes = get_all_palindromes( max_factor, min_factor )
    return (min(all_palindromes),all_palindromes[min(all_palindromes)])

def get_all_palindromes( max_factor, min_factor=0 ):
    return { n*N:{n,N} for n in range( min_factor, max_factor+1 )
             for N in range( n, max_factor+1 )
             if is_palindrome( n*N ) }

def is_palindrome( N ):
    return str(N) == ''.join(reversed(list(str(N))))
