def on_square(squares):
    return 2 ** (squares - 1)

def total_after(squares):
    return sum([on_square(i) for i in range(1, squares + 1)])
    
    
    
