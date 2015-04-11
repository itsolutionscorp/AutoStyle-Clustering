def score(word):
    scoring_dict = init_dict()

    return sum(scoring_dict.get(char, 0) for char in word.lower())


def init_dict():
    scoring_tuples = [
        ("aeioulnrst", 1),
        ("dg", 2),
        ("bcmp", 3),
        ("fhvwy", 4),
        ("k", 5),
        ("jx", 8),
        ("qz", 10)
    ]
    return {char: value
            for string, value in scoring_tuples
            for char in string}
