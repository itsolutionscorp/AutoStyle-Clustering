# Fill in the squares
SQUARES = [0, 1]
for _ in range(63):
    SQUARES.append(SQUARES[-1] * 2)


def on_square(num):
    return SQUARES[num]


def total_after(square_num):
    return sum(SQUARES[:square_num + 1])
