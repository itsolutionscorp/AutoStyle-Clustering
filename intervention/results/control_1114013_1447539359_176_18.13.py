def num_common_letters(goal_word, guess):
    """Find the number of common letters between the words

    Repeated words count only once.

    >>> num_common_letters("least", "steal")
    5
    >>> num_common_letters("aaaa", "aa")
    1
    """
    count = 0
    goal_word = set(goal_word)
    guess = set(guess)
    for char in goal_word:
        if char in guess:
            count += 1
    return count
