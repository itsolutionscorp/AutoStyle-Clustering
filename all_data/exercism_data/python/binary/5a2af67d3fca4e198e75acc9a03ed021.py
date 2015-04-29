def parse_binary(s):
    if s.count('1') + s.count('0') != len(s):
        raise ValueError("Inappropriate input for string representation of binary number.")
    
    return sum([2**i for i in range(len(s)) if s[-(i+1)] == '1'])
