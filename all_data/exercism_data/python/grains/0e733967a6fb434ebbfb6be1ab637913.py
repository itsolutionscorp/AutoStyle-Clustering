def on_square(number):
  
    return 2**(number-1)

def total_after(number):
    total = 0

    for num in range(1,number+1):
        total += on_square(num)
    
    return total
