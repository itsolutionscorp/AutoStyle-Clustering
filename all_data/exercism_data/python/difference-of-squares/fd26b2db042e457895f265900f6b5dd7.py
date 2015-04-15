def sum_of_squares(num):
    counter=1
    ans=0
    while counter < num+1:
        ans+=counter**2
        counter+=1
    return ans

def square_of_sum(num):
    counter=1
    ans=0
    while counter < num+1:
        ans+=counter
        counter+=1
    return ans**2

def difference(num):
    return abs(sum_of_squares(num) - square_of_sum(num))
    
