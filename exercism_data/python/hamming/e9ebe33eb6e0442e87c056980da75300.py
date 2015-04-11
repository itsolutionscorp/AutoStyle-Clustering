#hamming.py

def hamming(x,y):
    #starting the diff counter that will be returned by hamming()
 
    c=0
 
    #Running the for loop in check_same() will generate a range error if x and y are of different lengths, 
    #so this checks the lengths to decide which order x and y will be passed to check_same() while adding
    #the arith diff to the counter 'c'

    if len(x)>len(y):
	c+=len(x)-len(y)
        c+=check_same(x,y)
    else:
        c+=len(y)-len(x)
	c+=check_same(y,x)
    return c    


def check_same(a,b):
    
    #this runs a for loop on the shortest length list of either x or y (based on the if/else in hamming()
    #then does a simple check against the same placeholder value of each list, keeping a tally in counter 'z'

    z=0
    a,b=list(a),list(b)
    for i in range(len(b)):
	if a[i]!=b[i]:
	    z+=1
    return z
