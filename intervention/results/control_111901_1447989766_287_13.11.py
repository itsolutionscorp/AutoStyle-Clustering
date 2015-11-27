def num_common_letters(goal_word, guess):
    goal_letters = []
    checked = []
    common = 0
    for letter in goal_word:
        goal_letters.append(letter)
    for letter in guess:
        if letter in goal_letters and letter not in checked:
            common += 1
            checked.append(letter)
    return common