def on_square(index):
    return  2 ** (index -1)

def total_after(index):
    if index == 1:
        return index
    else:
        return on_square(index) + total_after(index-1)
