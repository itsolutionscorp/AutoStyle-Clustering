def on_square(square_number):
    return int('1'+'0'*(square_number-1),2)

def total_after(square_limit):
    total = 0
    for i in range(1,square_limit+1):
        total += on_square(i)
    return total
