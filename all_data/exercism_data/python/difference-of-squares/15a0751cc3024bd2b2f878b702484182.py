def sum_of_squares(N):
 return N*(N+1)*(2*N+1)/6

def square_of_sum(N):
 return N*N*(N+1)*(N+1)/4

def difference(N):
 return abs(sum_of_squares(N)-square_of_sum(N))
