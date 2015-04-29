
def parse_binary(input):
    if set(input) - {'0', '1'}:
        raise ValueError("Input not a binary string.")

    return sum(2**power
               for power, bit in enumerate(reversed(input))
               if bit == '1')
               
