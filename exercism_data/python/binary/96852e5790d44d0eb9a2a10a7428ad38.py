def parse_binary(b_str):
    indices = []

    for i, b in enumerate(reversed(b_str)):
        if b not in [ "0", "1" ]:
            # not valid binary
            raise ValueError("input string isn't valid binary")

        if b == "1":
            indices.append(i)

    return sum( 2**power for power in indices )
