def num_common_letters(goal_word, guess):
    goal_word_lst = get_list(goal_word)
    guess_lst = get_list(guess)
    correct_letters = []
    for i in range(len(goal_word_lst)):
        correct_letters += [0]
        for j in range(len(guess_lst)):
            if (goal_word_lst[i] == guess_lst[j]):
                correct_letters[i] = 1
    sum_correct = 0
    for i in range(len(correct_letters)):
        if correct_letters[i] == 1:
            sum_correct += 1
    return sum_correct
