def score(word):
    scoring_dict = init_dict()
    score = 0

    # send to lowercase
    word = word.lower()

    for char in word:
        try:
            score += scoring_dict[char]
        except KeyError:
            continue

    return score


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
    scoring_dict = {}
    for score_tuple in scoring_tuples:
        string, value = score_tuple
        for character in string:
            scoring_dict[character] = value

    return scoring_dict
