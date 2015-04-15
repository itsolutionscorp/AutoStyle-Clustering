# CS 61A Spring 2014
# Name: Leon Eng
# Login: cs61a-fh

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
    return list(s)
def make_word_from_list(lst):
    """Creates an instance of the word ADT from the list of characters lst.
    """
    "*** YOUR CODE HERE ***"
    return lst
def get_string(word):
    """Returns the string representation of word.
    
    >>> get_string(make_word_from_string('hello'))
    'hello'
    >>> get_string(make_word_from_list(['w', 'o', 'r', 'l', 'd']))
    'world'
    """
    "*** YOUR CODE HERE ***"
    if word == []:
        return ''
    else:
        return word[0] + get_string(word[1:])
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
    common_letters = 0
    goalcount = 0
    if get_list(guess) == get_list(goal_word): #if list == list
        return len(get_list(guess))
    else:
        for item in get_list(guess): #going through every item in guess
            if get_list(goal_word)[goalcount] in get_list(guess):
                common_letters += 1
                #if letter 1 of goal inside guess
            goalcount += 1 
            #start next letter of goal to search in guess while loop
        return common_letters




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
    def call_common(guess):
        if len(get_list(guess)) > len(get_list(goal_word)):
            return bad_num_letters
        elif not is_valid_guess(get_list(guess)): #check if it is valid guess
            return not_a_word
        elif guess == goal_word: #check if you hit goal word
            return win_message
        else:
            return num_common_letters(goal_word, guess)
    return call_common


def add_to_all(lst, elem): #returns the big list
    if lst == []:
        return []
    else:
        new_list = add_to_all(lst[1:], elem)
        return lst[0] + [elem] + new_list
#    new_list = []
#    for item in lst:
#
#   new_list = new_list + 
#   return new_list
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
    if n == 0:
        return [[]]
    elif lst == []:
        return []
    else:
        giraffe, pizza = lst[0], lst[1:]
        sub = subsets(pizza, n-1) #[[2],[3]]
        sub2 = subsets(pizza, n) #[[2,3]]
        sub3 = []
        for s in sub:
            sub3 += [[giraffe] + s]
        return sub3 + sub2
    # if n == 1:
    #     new_list = []
    #     for elem in lst:
    #         new_list += [[elem]]
    #     return new_list
    # else:
    #     new_list2 = []
    #     for every in subsets(lst, n-1):
    #         for elem in lst:
    #             new_list2 += [[elem] + every]
    #     for elem in new_list2:
    #         for sort in elem:
    #             if sorted(elem)[0] == sorted(elem)[1]
    #     return new_list2

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
    if num_common_letters(letter_list,guess) == score:
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
    master_list = []
    for sublst in possible_subsets:
        if num_common_letters(word, sublst) == score:
            master_list.append(sublst)
    return master_list



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
    commonalities = possible_subsets[0] #['a','b','c']
    no_common = letters.copy() #copies letters
    for sublst in possible_subsets: #for every sublst
        for let in commonalities: #for every LETTER
            if let not in sublst: #if not in sublst
                commonalities.remove(let) #remove it
                #move onto next sublst
    for let in letters: #for every LETTER of answer
        for sublst in possible_subsets: #for every sublst   
                if let in sublst and let in no_common: #if LETTER not in sublst + not removed yet
                    no_common.remove(let) #remove LETTER from answer
    for let in commonalities:
        if let not in letters:
            commonalities.remove(let)
    present = commonalities
    not_present = no_common
    return present, not_present



    # for sublst in possible_subsets: #[] [] [] []
    #     for i in sublst:
    #         if i in letters:
    #             present.append(i)
    # for let in letters:
    #     for sublst in possible_subsets:
    #         if let not in sublst:
    #             not_present.append(let)




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

# @main
# def play_game(word=""):
#     """
#     Call this function with no arguments to play with a random word,
#     or a word between 4 and 7 letters to play with your own word.
#     """
#     if not word:
#         word = choose_random_word()
#     elif not is_valid_goal_word(word):
#         return not_a_word
#     print('Playing with a', len(get_string(word)), 'letter word.')
#     word_master = make_word_master(word)
#     possible_subsets = subsets(letters, len(get_string(word)))
#     result = ''
#     while result != win_message:
#         print('Enter a word to get its score, h for a hint, or q to quit.')
#         next_string = sys.stdin.readline().strip().lower()
#         if next_string == 'q':
#             print('The word was', get_string(word))
#             return

#         if next_string == 'h':
#             print_deductions(possible_subsets)
#         else:
#             guess = make_word_from_string(next_string)
#             result = word_master(guess)
#             if type(result) == type(''):
#                 print(result)
#             else:
#                 possible_subsets = filter_subsets(guess, result, possible_subsets)
#                 print('The word', next_string, 'has', result, 'letters in common')


