def num_common_letters(goal_word, guess):
    repeats=count=0
    guess_array=get_list(guess)
    length=len(guess)
    for letter in guess:
        for otherletter in goal_word:
            if letter==otherletter:
                count+=1
    return count
