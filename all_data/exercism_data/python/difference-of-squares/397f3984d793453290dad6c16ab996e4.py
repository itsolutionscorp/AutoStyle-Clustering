def difference(term):
    return square_of_sum(term) - sum_of_squares(term)

def square_of_sum(term):
    # Triangle number
    total = term * (term + 1) / 2
    return total * total

def sum_of_squares(term):
    # Square pyramidal number
    return term * (term + 1) * (2 * term + 1) / 6
