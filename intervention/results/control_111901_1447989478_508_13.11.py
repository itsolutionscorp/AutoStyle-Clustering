def num_common_letters(goal_word, guess):
    letters_in_goal_word = []
    letters_already_checked = []
    common_letters = 0
    for letter in goal_word:
        letters_in_goal_word.append(letter)
    for letter in guess:
        if letter in letters_in_goal_word and letter not in letters_already_checked:
            common_letters += 1
            letters_already_checked.append(letter)
    return common_letters