"""Write a program that calculates the number of grains of wheat on a chessboard given that the number on each square doubles."""

def on_square(square):
    "how many grains were on each square"
    return 2**(square-1)    
    
def total_after(square):
    "the total number of grains"
    return sum(2**i
               for i in range(square))   
