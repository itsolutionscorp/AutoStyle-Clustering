# CS 61A Spring 2014
# Name: Nathan Martin Ybarrolaza
# Login: cs61a-im

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
    indexer = 0
    word = []
    while indexer < len(s):
        word.append(s[indexer])
        indexer += 1
    return word


def make_word_from_list(lst):
    """Creates an instance of the word ADT from the list of characters lst.
    """
    "*** YOUR CODE HERE ***"
    indexer = 0
    temp_word = []
    while indexer < len(lst):
        temp_word = temp_word + (lst[indexer])
        indexer += 1
    return temp_word

def get_string(word):
    """Returns the string representation of word.
    
    >>> get_string(make_word_from_string('hello'))
    'hello'
    >>> get_string(make_word_from_list(['w', 'o', 'r', 'l', 'd']))
    'world'
    """
    "*** YOUR CODE HERE ***"
    indexer = 0
    temp_string = ''
    while indexer < len(word):
        temp_string = temp_string + (word[indexer])
        indexer += 1
    return temp_string


def get_list(word):
    """Returns the list of characters representation of word.
    
    >>> get_list(make_word_from_string('hello'))
    ['h', 'e', 'l', 'l', 'o']
    >>> get_list(make_word_from_list(['w', 'o', 'r', 'l', 'd']))
    ['w', 'o', 'r', 'l', 'd']
    """
    "*** YOUR CODE HERE ***"
    return word



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
    goal_length = len(goal_word)
    guess_length = len(guess)
    temp_goal = list(goal_word)
    temp_guess = list(guess)
    indexer_guess = 0
    number_letters = 0
    while indexer_guess < guess_length:
        indexer_goal = 0
        while indexer_goal < goal_length and temp_goal[indexer_goal] != temp_guess[indexer_guess]:
            indexer_goal += 1
        if indexer_goal == goal_length:
            indexer_guess += 1
        else:
            temp_goal.remove(temp_goal[indexer_goal])
            temp_guess.remove(temp_guess[indexer_guess])
            number_letters = number_letters + 1
            goal_length -= 1
            guess_length -= 1            
    return number_letters



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
    #print(len(goal_word))
    def how_well_does_it_work(guess):
        if len(goal_word) != len(guess):
            #print(len(goal_word),len(guess))
            #print(goal_word,guess)
            return bad_num_letters
        elif is_valid_guess(guess) == False:
            return not_a_word
        elif guess == goal_word:
            return win_message
        else:
            return num_common_letters(goal_word, guess)
    return how_well_does_it_work




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

    def list_adder(elem, lst):
        temp_list = list(lst)
        indexer = 0
        while indexer < len(lst):
            temp_list[indexer] = [elem] + temp_list[indexer] #this assumes that lst[indexer] is a list
            indexer += 1
        return temp_list

    def all_sets(lst):
        inter_list = list(lst)
        if lst == []:
            return[[]]
        else:
            temp_list = all_sets(inter_list[1:])
            return temp_list + list_adder(lst[0], temp_list)

    temp_list = list(all_sets(lst))
    subsets = len(temp_list)
    filter_indexer = 0
    while filter_indexer < subsets:
        if len(temp_list[filter_indexer]) != n:
            del temp_list[filter_indexer]
            subsets -= 1
        else:
            filter_indexer += 1
    return temp_list

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
    return num_common_letters(letter_list, guess) == score


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
    temp_list = list(possible_subsets)
    subsets = len(temp_list)
    filter_indexer = 0
    while filter_indexer < subsets:
        if compatible(word, score, temp_list[filter_indexer]):
            filter_indexer += 1
        else:
            del temp_list[filter_indexer]
            subsets -= 1
    return temp_list


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
    letter_indexer = 0
    while letter_indexer < len(letters):
        checker_indexer = 0
        letter_present = True
        letter_not_present = True
        while  checker_indexer < len(possible_subsets):
            if letters[letter_indexer] in possible_subsets[checker_indexer]:
                letter_present = letter_present and True
                letter_not_present = False
            else:
                letter_present = False
                letter_not_present = letter_not_present and True
            checker_indexer += 1
        if letter_present == True:
            present = present + [letters[letter_indexer]]
        if letter_not_present == True:
            not_present = not_present + [letters[letter_indexer]]
            
        letter_indexer += 1
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


