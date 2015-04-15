def numeral(arabic):
    # Uses a greedy algorithm
    roman = ""
    atoms = [
        ("M", 1000),
        ("CM", 900),
        ("D", 500),
        ("CD", 400),
        ("C", 100),
        ("XC", 90),
        ("L", 50),
        ("XL", 40),
        ("X", 10),
        ("IX", 9),
        ("V", 5),
        ("IV", 4),
        ("I", 1)
    ]

    remainder = arabic
    for number_tuple in atoms:
        roman += number_tuple[0] * (remainder // number_tuple[1])
        remainder = remainder % number_tuple[1]

    return roman
