import numpy as np
import re

def board(blank):
    #perform board validation
    test_board(blank)
    #create new board --> turn board into array
    new_blank = np.array([(list(row)) for row in blank])
    #append blank board with values and return
    filled = check_spots(new_blank)
    return [''.join(row) for row in filled.tolist()]
    
   
def test_board(blank):
#will run tests to see if input board is valid    
    for row in blank:
    #checks that all rows in board are of equal length
        if len(row) != len(blank[0]):
            raise ValueError
    for row in blank[0::len(blank)-1]:
    #checks that first and last lines follow '+[-]+' format
        if not re.match(r"^\+-+\+$", row):
            raise ValueError
    for row in blank[1:len(blank)-1]:
    #checks that all other lines in board follow '|[*_]|' format
        if not re.match(r"^\|[* ]+\|$", row):
            raise ValueError
    return True
        
    
def check_spots(blank_array):
#checks each spot in array to see if blank, appends with surrounding mine number
    n = 0
    for row in blank_array:
        for i in row:
            spot_lst = [n+1, n-1, n+len(row), n-len(row), n+len(row)+1,
                        n-len(row)+1, n+len(row)-1, n-len(row)-1]
            if i == " ":
                count = 0
                for j in spot_lst:
                    if j >= 0:
                        if blank_array.item(j) == '*':
                            count += 1
                if count != 0:
                    blank_array.itemset(n, count)
            n += 1
    return blank_array
