def on_square(arg):
    return 0x01 << (arg - 1)

def total_after(arg):
    return (0x01 << arg) - 1
