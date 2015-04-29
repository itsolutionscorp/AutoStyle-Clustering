def on_square(sq_num):
    return 2**(sq_num-1)

def total_after(sq_num):
    return sum(on_square(i) for i in range(1, sq_num+1))
