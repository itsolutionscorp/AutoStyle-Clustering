def num_common_letters(goal_word, guess):
    goal_characters = get_list(goal_word)
    guess_characters = get_list(guess)
    common = []
    for character in guess_characters:
        if character in goal_characters and character not in common:
            common += [character]
        else:
            common += []
    return len(common)
