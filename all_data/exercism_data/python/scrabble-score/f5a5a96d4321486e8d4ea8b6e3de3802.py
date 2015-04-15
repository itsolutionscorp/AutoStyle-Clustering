letters_by_score = {
    1: "aeioulnrst",
    2: "dg",
    3: "bcmp",
    4: "fhvwy",
    5: "k",
    8: "jx",
    10: "qz"
}
scores_by_letter = { letter: score for score, chars in letters_by_score.iteritems() for letter in chars }

def score(word):
    word = word.strip().lower()

    return sum(scores_by_letter.get(c, 0) for c in word)
