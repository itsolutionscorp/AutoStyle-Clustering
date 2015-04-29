def on_square(square_num):
    # Geometric sequence formula: Tn = a*r**(n-1) (Being a Maths teacher apparently comes in handy in coding)
    if not square_num in range(1,65):
        raise ValueError("invalid square number")
    else:
        return (2**(square_num - 1))

def total_after(square_num):
    # Geometric series formula: Sn = (a * (r**n - 1)) / (r - 1)
    if not square_num in range(1,65):
        raise ValueError("invalid square number")
    else:
        return (2**square_num - 1)
