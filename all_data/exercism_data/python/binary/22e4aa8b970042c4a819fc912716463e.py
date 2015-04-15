def parse_binary(binary):
    power = 1
    decimal = 0

    for i in reversed(binary):
        if i != '0' and i != '1':
            raise ValueError()

        i = int(i)

        decimal += i * power
        power *= 2

    return decimal
