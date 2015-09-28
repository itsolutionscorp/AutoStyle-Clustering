def num_common_letters(goal_word, guess):
    num_common = 0
    for elem in get_list(goal_word):
        if is_in_list(elem, get_list(guess)):
            num_common += 1
    return num_common
def is_in_list(elem,lst):
    for character in lst:
        if character == elem:
            return True  
    return False
