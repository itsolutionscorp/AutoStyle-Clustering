def on_square(what):
    return pow(2,what-1)

def total_after(double):
    suma=0
    for i in range(double):
        suma+=on_square(i+1)
    return suma
