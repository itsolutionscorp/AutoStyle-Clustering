def num_common_letters(goal_word, guess):
    goalword = get_string(goal_word) #don't use goal_word or guess directly
    guessword = get_string(guess)
    all_common_letters = [x for x in guessword if x in goalword]  #all the common letters
    letters = []  #all common letters without duplicates
    for elem in all_common_letters:
        if elem not in letters:
            letters += [elem]
    return len(letters)
