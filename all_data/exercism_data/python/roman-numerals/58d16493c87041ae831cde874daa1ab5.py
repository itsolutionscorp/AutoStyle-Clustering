def numeral(arabic):
    NUMERALS = [(1000, "M"), (900, "CM"), (500, "D"),
                (400, "CD"), (100, "C"), (90, "XC"),
                (50, "L"), (40, "XL"), (10, "X"),
                (9, "IX"), (5, "V"), (4, "IV"), (1, "I")]
    working_num = arabic
    result_set = list()
    while working_num > 0:
        for arabic, numeral in NUMERALS:
            for _ in range(working_num // arabic):
                result_set.append(numeral)
            working_num %= arabic
    return "".join(result_set)
