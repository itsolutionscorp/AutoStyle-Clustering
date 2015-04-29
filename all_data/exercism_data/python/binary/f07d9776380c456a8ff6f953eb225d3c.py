def parse_binary(num_str):
    if any(c not in "10" for c in num_str):
            raise ValueError("Not a valid Binary number string")
    return sum([2**int(x) for x in range(len(num_str)) if num_str[::-1][x] == '1'])
