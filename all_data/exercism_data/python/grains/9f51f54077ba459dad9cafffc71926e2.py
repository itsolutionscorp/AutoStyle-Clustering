# @profile
def on_square(sq):
    return 2**(sq-1)


# @profile
def total_after(sq):
    return sum(on_square(n) for n in range(1, sq+1))


if __name__ == "__main__":
    print(on_square(64))
    print(total_after(64))
