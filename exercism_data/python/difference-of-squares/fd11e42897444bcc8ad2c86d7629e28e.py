def square_of_sum(nInput):
    return ((nInput * (nInput + 1))/2)*((nInput * (nInput + 1))/2)

def sum_of_squares(nInput):
    return ((nInput*(nInput+1)*(2*nInput+1))/6)

def difference(nInput):
    return square_of_sum(nInput) - sum_of_squares(nInput)
