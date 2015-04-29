# CS 61A Spring 2014
# Name: Aditi Ganpule
# Login: cs61a-fw

from ucb import main

letters = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
           'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
           'u', 'v', 'w', 'x', 'y', 'z']
bad_num_letters = "Wrong number of letters."
not_a_word = "Not a valid word."
win_message = "Congratulations! You win!"


class PairError(Exception):
    pass

def cons(a, b):
    def answer(m):
        if m == 'car':
            return a
        elif m == 'cdr':
            return b
        else:
            # This is a way for us to create our own error messages.
            raise PairError('You can only use car or cdr on a pair!')
    return answer

def car(p):
    return p('car')

def cdr(p):
    return p('cdr')


# Implementation of linked lists using cons

empty = lambda: 42

def link(element, lst):
    return cons(element, lst)

def first(lst):
    return car(lst)

def rest(lst):
    return cdr(lst)

def print_linked_list(lst):
    print(linked_list_to_str(lst))

def linked_list_to_str(lst):
    s = '< '
    while lst != empty:
        s = s + repr(first(lst)) + ' '
        lst = rest(lst)
    return s[:-1] + ' >'

def make_word_from_string(s):
    """Creates an instance of the word ADT from the string s.
    """
    return link(s,empty)

def make_word_from_list(lst):
    """Creates an instance of the word ADT from the list of characters lst.
    """
    return link(lst,empty)

def get_string(word):
    """Returns the string representation of word.
    
    >>> get_string(make_word_from_string('hello'))
    'hello'
    >>> get_string(make_word_from_list(['w', 'o', 'r', 'l', 'd']))
    'world'
    """
    if isinstance(first(word),str):
        return first(word)
    else:
        string = ''
        for i in range(len(first(word))):
            string += str(first(word)[i])
        return string


def get_list(word):
    """Returns the list of characters representation of word.
    
    >>> get_list(make_word_from_string('hello'))
    ['h', 'e', 'l', 'l', 'o']
    >>> get_list(make_word_from_list(['w', 'o', 'r', 'l', 'd']))
    ['w', 'o', 'r', 'l', 'd']
    """
    if isinstance(first(word),str):
        lst = []
        for i in range(len(first(word))):
            lst += [first(word)[i]]
        return lst
    else:
        return first(word)


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
    goal_word_list = get_list(goal_word)
    guess_list = get_list(guess)
    if goal_word_list == guess_list:
        return len(guess_list)
    same,i = 0,0
    while i < len(goal_word_list):
        index = 0
        while index < len(guess_list) and i < len(goal_word_list):
            if goal_word_list[i] == guess_list[index]:
                same += 1
                i += 1
                index = 0
            else:
                index += 1
        i += 1
    return same
        


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
    def word_checker(guess_word):
        guess_word_list = get_list(guess_word)
        goal_word_list = get_list(goal_word)
        if goal_word_list == guess_word_list:
            return win_message
        elif len(goal_word_list) != len(guess_word_list):
            return bad_num_letters
        elif not is_valid_guess(guess_word):
            return not_a_word
        else:
            return num_common_letters(goal_word, guess_word)
    return word_checker





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
    def list_adder(l,lst):
        for i in range(len(lst)):
            lst[i] = [l] + lst[i]
        return lst

    if lst == [] and n == 0:
        return [[]]
    elif lst == []:
        return []
    elif n == 0:
        return [[]]
    else:
        return list_adder(lst[0] ,subsets(lst[1:],n-1)) + subsets(lst[1:],n)

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
    if num_common_letters(make_word_from_list(letter_list), guess) == score:
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
    new_lst = []
    for i in range(len(possible_subsets)):
        if compatible(word,score,possible_subsets[i]):
            new_lst += [possible_subsets[i]]
    return new_lst
    

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
    present, not_present = [],[]
    i = 0
    while i < len(letters):
        times_in, times_not, k = 0,0,0
        while k < len(possible_subsets):
            if letters[i] in possible_subsets[k]:
                times_in += 1
            elif letters[i] not in possible_subsets[k]:
                times_not += 1
            if times_in == len(possible_subsets):
                present += [letters[i]]
            if times_not == len(possible_subsets):
                not_present += [letters[i]]
            k += 1
        i += 1
    
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
    return random.choice(master_words)

import sys

@main
def play_game(word=""):
    """
    Call this function with no arguments to play with a random word,
    or a word between 4 and 7 letters to play with your own word.
    """
    if not word:
        word = make_word_from_string(choose_random_word())
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
            word = make_word_from_string(next_string)
            result = word_master(word)
            if type(result) == type(''):
                print(result)
            else:
                possible_subsets = filter_subsets(word, result, possible_subsets)
                print('The word', next_string, 'has', result, 'letters in common')


