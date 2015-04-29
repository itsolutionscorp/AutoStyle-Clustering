class grain_counter():
    def __init__(self):
        self.squares = [1]
        self.totals = [1]
        for i in range(1,64):
            self.squares.append(2*self.squares[-1])
            self.totals.append(self.totals[-1]+self.squares[-1])


_grain_counter = grain_counter()


def on_square(n):
    return _grain_counter.squares[n-1]

def total_after(n):
    return _grain_counter.totals[n-1]
    
