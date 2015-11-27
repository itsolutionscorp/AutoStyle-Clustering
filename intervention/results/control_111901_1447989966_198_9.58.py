def num_common_letters(goal_word, guess):
    checked = []
    common = 0
    for letter in guess:
        if letter in goal_word and letter not in checked:
            common += 1
            checked.append(letter)
    return common