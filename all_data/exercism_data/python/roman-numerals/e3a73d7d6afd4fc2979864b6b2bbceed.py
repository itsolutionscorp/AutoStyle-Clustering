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
    for letters, number in atoms:
        roman += letters * (remainder // number)
        remainder = remainder % number

    return roman
