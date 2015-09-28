def num_common_letters(goal_word, guess):
    if goal_word == [] or guess == []:
        return 0
    if goal_word[0] in guess:
        return 1 + num_common_letters(goal_word[1:], guess)
    else:
        return num_common_letters(goal_word[1:], guess)
