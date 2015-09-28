def num_common_letters(goal_word, guess):
    if guess == '' or goal_word == '':
        return 0
    else:
        if goal_word[0] in guess:
            i = 0
            while guess[i] != goal_word[0]:
                i += 1
            guess = guess[0:i] + guess[i+1:]
            return 1 + num_common_letters(goal_word[1:], guess)
        else:
            return num_common_letters(goal_word[1:], guess)
