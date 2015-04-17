# CS 61A Spring 2014
# Name: Aaron Tang
# Login: cs61a-fl

from ucb import main

letters = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
           'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
           'u', 'v', 'w', 'x', 'y', 'z']
bad_num_letters = "Wrong number of letters."
not_a_word = "Not a valid word."
win_message = "Congratulations! You win!"


def make_word_from_string(s):
    """Creates an instance of the word ADT from the string s.
    """
    "*** YOUR CODE HERE ***"

    word = s
    return word


def make_word_from_list(lst):
    """Creates an instance of the word ADT from the list of characters lst.
    """
    "*** YOUR CODE HERE ***"

    index = 0
    word = ""
    while index < len(lst):
        word += str(lst[index])
        index += 1
    return word

def get_string(word):
    """Returns the string representation of word.
    
    >>> get_string(make_word_from_string('hello'))
    'hello'
    >>> get_string(make_word_from_list(['w', 'o', 'r', 'l', 'd']))
    'world'
    """
    "*** YOUR CODE HERE ***"

    return word
    # My ADT already has word as a string.

def get_list(word):
    """Returns the list of characters representation of word.
    
    >>> get_list(make_word_from_string('hello'))
    ['h', 'e', 'l', 'l', 'o']
    >>> get_list(make_word_from_list(['w', 'o', 'r', 'l', 'd']))
    ['w', 'o', 'r', 'l', 'd']
    """
    "*** YOUR CODE HERE ***"

    list_of_characters = []
    index = 0
    while index < len(word):
        list_of_characters += [word[index]]
        index += 1
    return list_of_characters

def num_common_letters(goal_word, guess):
    """Returns the number of letters in goal_word that are also in guess.
    As per the rules of the game, goal_word cannot have any repeated
    letters, but guess is allowed to have repeated letters.
    goal_word and guess are assumed to be of the same length.
    goal_word and guess are both instances of the word ADT.

    >>> mwfs, mwfl = make_word_from_string, make_word_from_list
    >>> num_common_letters(mwfs('steal'), mwfs('least'))
    5
    >>> num_common_letters(mwfs('steal'), mwfl(['s', 't', 'e', 'e', 'l']))
    4
    >>> num_common_letters(mwfl(['s', 't', 'e', 'a', 'l']), mwfs('thief'))
    2
    >>> num_common_letters(mwfl(['c', 'a', 'r']), mwfl(['p', 'e', 't']))
    0
    """
    "*** YOUR CODE HERE ***"

    guess_list = get_list(guess)
    goal_list = get_list(goal_word)
    # Turns the guess and goal_word into lists so we can easily search
    # elements by indices in the list.

    def letter_checker(guess_list, goal_list, guess_index, goal_index):
        
        def repeat_checker(guess_list, guess_index, repeat_index):
            # THe idea is, for each letter, to compare with all other
            # previously-checked letters, and if there is an overlap,
            # then stop checking the current letter because you have
            # already decided if it counted as a 0 or a 1.
            if repeat_index < 0:
                return False
                # Base case when you have finished checking all of the 
                # previously-checked letter_checker'd letters.
            elif guess_list[guess_index] == guess_list[repeat_index]:
                return True
                # Identifies if you have an overlapped letter.
            else:
                return repeat_checker(guess_list, guess_index, repeat_index - 1)
                # Recursively checks the next most recently checked letter
                # to see if it matches the current guess_index letter.

        if (guess_index == len(guess_list)):
            return 0
            # When all of the elements in guess_list have been checked,
            # end the recursive call.

        elif repeat_checker(guess_list, guess_index, guess_index - 1) == True:
            return 0 + letter_checker(guess_list, goal_list, guess_index + 1, 0)
            # If a letter is identified as having been checked before,
            # skip this letter and letter_checker the next element
            # in the guess_list.

        elif (goal_index == len(goal_list)):
            return 0 + letter_checker(guess_list, goal_list, guess_index + 1, 0)
            # If a letter has been compared with every element in goal_list
            # without any matches, then you know that that guess_list
            # element is not found in goal_list, so mvoe on to checking
            # the next element in guess_list.

        elif guess_list[guess_index] == goal_list[goal_index]:
            return 1 + letter_checker(guess_list, goal_list, guess_index + 1, 0)
            # If a letter in guess_list matches a letter in goal_list,
            # you know already that the letter is found in both goal and
            # guess words, so you can mark this as a match and move on
            # to checking the next element in guess_list.

        else:
            return letter_checker(guess_list, goal_list, guess_index, goal_index + 1)
            # Recursively compares the current letter in guess_list
            # with all of the letters in goal_list.

    return letter_checker(guess_list, goal_list, 0, 0)
    # Start the recursive call by checking the first letter in 
    # guess_list with the first letter in goal_list.


def make_word_master(goal_word):
    """Takes in the word to be guessed and returns a function which
    takes in a guess and does what the word master does, that is:
      If the guess is of incorrect length, it returns bad_num_letters.
      If the guess is correct, it returns win_message
      Otherwise, it returns the number of letters in common.

    >>> mwfs = make_word_from_string
    >>> foo = make_word_master(mwfs('least'))
    >>> foo(mwfs('water'))
    3
    >>> foo(mwfs('player')) == bad_num_letters
    True
    >>> foo(mwfs('steel'))
    4
    >>> foo(mwfs('steal'))
    5
    >>> foo(mwfs('aaaaa')) == not_a_word
    True
    >>> foo(mwfs('least')) == win_message
    True
    """
    "*** YOUR CODE HERE ***"

    def game_options(guess):
        if is_valid_guess(guess) == False:
            return not_a_word
            # Must be a valid guessable word
        elif len(get_string(goal_word)) != len(get_string(guess)):
            return bad_num_letters
            # If the guess is of incorrect length.
            # Use get_string to avoid the Data Abstraction Violation!
        elif get_string(goal_word) == get_string(guess):
            return win_message
            # If the guess is correct
        else:
            return num_common_letters(goal_word, guess)
            # Otherwise, returns the number of letters in common

    return game_options

def subsets(lst, n):
    """Returns all subsets of lst of size exactly n in any order.
    lst is a Python list, and n is a non-negative integer.

    >>> three_subsets = subsets(list(range(5)), 3)
    >>> three_subsets.sort()  # Uses syntax we don't know yet to sort the list.
    >>> for subset in three_subsets:
    ...     print(subset)
    [0, 1, 2]
    [0, 1, 3]
    [0, 1, 4]
    [0, 2, 3]
    [0, 2, 4]
    [0, 3, 4]
    [1, 2, 3]
    [1, 2, 4]
    [1, 3, 4]
    [2, 3, 4]
    """
    "*** YOUR CODE HERE ***"


    def subset_checker(lst, n, subset_part, final_subsets):
        # print("List, n, subset_part, final:", lst, n, subset_part, final_subsets)
        if n > len(lst):
            subset_part += []
        elif n == 0:
            final_subsets += [subset_part]
        #    print("We have our first subset!", lst, n, subset_part, final_subsets)
        else:
            subset_checker(lst[1:], n - 1, subset_part + [lst[0]], final_subsets) + subset_checker(lst[1:], n, subset_part, final_subsets)
            # subset_checker(lst[1:], n, subset_part, final_subsets)
        return final_subsets

    return subset_checker(lst, n, [], [])

    # This is super slow and inefficient. Will probably need to change.
    # The game in python -i hw4.py runs smoothly for 3 and 4 letter words,
    # takes a minute to load for 5 letter words, and probably forever
    # for 6+ letter words.


def compatible(guess, score, letter_list):
    """Returns True if it is possible for the word to get the score,
    assuming that the true word only contains letters from
    letter_list.
    Precondition:  len(word) == len(letter_list)

    >>> mwfs = make_word_from_string
    >>> compatible(mwfs('steal'), 5, ['l', 'e', 'a', 's', 't'])
    True
    >>> compatible(mwfs('blanket'), 6, ['a', 'b', 'e', 'l', 'n', 'r', 't'])
    True
    >>> compatible(mwfs('cool'), 4, ['c', 'o', 'l', 'd'])
    False
    >>> compatible(mwfs('found'), 1, ['d', 'e', 'f', 'g', 'h'])
    False
    """
    "*** YOUR CODE HERE ***"

    if num_common_letters(letter_list, guess) == score:
        return True
    else:
        return False


def filter_subsets(word, score, possible_subsets):
    """Returns the subsets for which word would get the given score.

    >>> word = make_word_from_string('steal')
    >>> sub1 = ['a', 'b', 'e', 'l', 's']
    >>> sub2 = ['b', 'e', 'l', 't', 'z']
    >>> sub3 = ['s', 't', 'e', 'a', 'l']
    >>> sub4 = ['b', 'l', 'e', 's', 't']
    >>> filter_subsets(word, 4, [sub1, sub2, sub3, sub4])
    [['a', 'b', 'e', 'l', 's'], ['b', 'l', 'e', 's', 't']]
    """
    "*** YOUR CODE HERE ***"

    index = 0
    correct_subsets = []
    while index < len(possible_subsets):
        if compatible(word, score, possible_subsets[index]) == True:
            correct_subsets += [possible_subsets[index]]
        index += 1
    return correct_subsets


def make_deductions(possible_subsets, letters):
    """Infers which letters must be in the word to be guessed, and
    which letters must not be in the word.
    A letter must be in the word if it is in every possible subset.
    A letter is not in the word if it is not in any possible subset.

    >>> letters = ['a', 'b', 'c', 'd', 'e', 'f']
    >>> subsets = [['a', 'b', 'c'], ['b', 'a', 'e'], ['e', 'a', 'c']]
    >>> present, not_present = make_deductions(subsets, letters)
    >>> present
    ['a']
    >>> not_present
    ['d', 'f']
    """
    present = []
    not_present = []
    "*** YOUR CODE HERE ***"

    def deduce(subsets, letter, s_index, in_s_index, counter):
        
        if s_index == len(subsets):
            return counter
            # Once you have finished looking through all the subsets to
            # compare to the current letter, return the number of subsets
            # that the current letter matched. Now go back to for loop.

        elif in_s_index == len(subsets[s_index]):
            return deduce(subsets, letter, s_index + 1, 0, counter)
            # If you have looked through one of the list elements in
            # subsets and found nothing, keep looking starting at the
            # 0th index of the next subset.

        elif letter == subsets[s_index][in_s_index]:
            counter += 1
            return deduce(subsets, letter, s_index + 1, 0, counter)
            # If the letter matches a letter in one of the subsets, then
            # you know it is automatically in that subset, so move onto
            # checking the letter with the next subset.
       
        else:
            return deduce(subsets, letter, s_index, in_s_index + 1, counter)
            # Recursively compare the current letter to all letters
            # in the current subset.

    for element in letters:
        if deduce(possible_subsets, element, 0, 0, 0) == 0:
            not_present += [element]
            # 0 in counter indicates that there were never any letter
            # matches in any of the subsets.

        elif deduce(possible_subsets, element, 0, 0, 0) == len(possible_subsets):
            present += [element]
            # The maximum possible counter is the length of the number
            # of the subsets. If they are equal, then the letter matched
            # in all of the subsets.

    return present, not_present


def print_deductions(possible_subsets):
    present, not_present = make_deductions(possible_subsets, letters)
    if present:
        print('The following letters must be present:', present)
    if not_present:
        print('The following letters must not be present:', not_present)
    if present == [] and not_present == []:
        print('No deductions can be made at this time.  Try some more words.')

import random

words = open('validguesses.txt', 'r')
guesser_words = [word.strip() for word in words]
master_words = [word for word in guesser_words if len(word) == len(set(word))]

def is_valid_guess(word):
    """Returns True if word is a valid word that can be guessed.
    """
    return get_string(word) in guesser_words

def is_valid_goal_word(word):
    """Returns True if word is a valid goal word.
    Similar to is_valid_guess, but must also not have repetitions.
    """
    return get_string(word) in master_words

def choose_random_word():
    return make_word_from_string(random.choice(master_words))

import sys

@main
def play_game(word=""):
    """
    Call this function with no arguments to play with a random word,
    or a word between 4 and 7 letters to play with your own word.
    """
    if not word:
        word = choose_random_word()
    elif not is_valid_goal_word(word):
        return not_a_word
    print('Playing with a', len(get_string(word)), 'letter word.')
    word_master = make_word_master(word)
    possible_subsets = subsets(letters, len(get_string(word)))
    result = ''
    while result != win_message:
        print('Enter a word to get its score, h for a hint, or q to quit.')
        next_string = sys.stdin.readline().strip().lower()
        if next_string == 'q':
            print('The word was', get_string(word))
            return

        if next_string == 'h':
            print_deductions(possible_subsets)
        else:
            guess = make_word_from_string(next_string)
            result = word_master(guess)
            if type(result) == type(''):
                print(result)
            else:
                possible_subsets = filter_subsets(guess, result, possible_subsets)
                print('The word', next_string, 'has', result, 'letters in common')
