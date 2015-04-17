def slices(str_digits, length):
    if(length > len(str_digits) or length <= 0):
        raise ValueError("Shame on you! length must be a positive integer i,\
         such that i <= len(input_str) ")
    digits = [int(digit) for digit in str_digits]
    return [digits[i : i + length] for i in range(len(digits) - length + 1)]
