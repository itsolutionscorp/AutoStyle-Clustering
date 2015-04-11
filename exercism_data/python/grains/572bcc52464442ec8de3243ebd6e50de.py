#Regular

def on_square(num):
    grains=1
    while num>1:
        grains=grains*2
        num=num-1
    return grains
        
def total_after(num):
    grains=0
    counter=1
    while num>0:
        grains=grains+counter
        counter=counter*2
        num=num-1
    return grains

#Readability
"""
def on_square(chess_board_square):
    grains_on_current_square=1
    current_square=1
    while current_square <= chess_board_square:
        current_square+=1
        grains_on_current_square=grains_on_current_square*2
    return grains_on_current_square
"""
