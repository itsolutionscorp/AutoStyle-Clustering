squares = 64
ref = [1] * squares

def populate():
    for n in range(1, squares):
        ref[n] = ref[n-1] * 2

def on_square(n):
    return ref[n-1]

def total_after(n):
    return sum(ref[0:n])

populate()
