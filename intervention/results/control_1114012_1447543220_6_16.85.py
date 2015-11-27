def num_common_letters(goal_word, guess):
    goal_letters = []
    guess_letters = []
    count= 0
    for i in goal_word:
        if i not in goal_letters:
            goal_letters.append(i)
    for i in guess:
        if i not in guess_letters:
            guess_letters.append(i)
    for i in guess_letters:
        if i in goal_letters:
            count += 1
    return count