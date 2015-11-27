def num_common_letters(goal_word, guess):
    """Find the number of common letters between the words

    Repeated words count only once.

    >>> num_common_letters("least", "steal")
    5
    >>> num_common_letters("aaaa", "aa")
    1
    """
    count = 0
    seen_chars = []
    for char in goal_word:
        if char not in seen_chars:
            if char in guess:
                count += 1
            seen_chars.append(char)
    return count
