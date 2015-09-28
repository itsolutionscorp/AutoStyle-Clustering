def num_common_letters(goal_word, guess):
    # if a letter is in the first word and a letter is in the second word, return the number of letters
    num_common = 0
    for y in goal_word:
        for el in guess:
            if y == el:
                num_common += 1
                break
    return num_common
mwfs = make_word_from_string
