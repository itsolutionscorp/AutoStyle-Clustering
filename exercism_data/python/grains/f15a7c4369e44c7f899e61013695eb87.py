
def on_square(power):
    return 2**(power-1)

def total_after(power):
    return sum([on_square(i) for i in range(1, power+1)])
