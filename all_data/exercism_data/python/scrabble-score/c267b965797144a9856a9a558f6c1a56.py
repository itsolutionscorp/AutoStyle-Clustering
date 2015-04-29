def score(word):

    scoring_tuples = [
        ("aeioulnrst", 1),
        ("dg", 2),
        ("bcmp", 3),
        ("fhvwy", 4),
        ("k", 5),
        ("jx", 8),
        ("qz", 10)
    ]

    # Create a scoring dictionary out of the above info
    scoring_dict = {char: value
                    for string, value in scoring_tuples
                    for char in string}

    # Get the score; if a character isn't in the dictionary,
    # default to zero.
    return sum(scoring_dict.get(char, 0) for char in word.lower())
