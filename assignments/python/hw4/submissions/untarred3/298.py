# CS 61A Spring 2014
# Name:
# Login:

from ucb import main

letters = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
           'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
           'u', 'v', 'w', 'x', 'y', 'z']
bad_num_letters = "Wrong number of letters."
not_a_word = "Not a valid word."
win_message = "Congratulations! You win!"


def make_word_from_string(s): # string ADT --> word ADT 
    """Creates an instance of the word ADT from the string s.
    """
    "*** YOUR CODE HERE ***"
    character_lst = [] 
    word_lst = []
    for i in s: 
        if i in letters: 
            character_lst.append(i)
    word_lst.append(''.join(character_lst))
    return word_lst 

def make_word_from_list(lst): # lst ADT --> word ADT
    """Creates an instance of the word ADT from the list of characters lst.
    """
    "*** YOUR CODE HERE ***"
    word_lst = [] 
    word_lst.append(''.join(lst))
    return word_lst

def get_string(word):  # word ADT --> string ADT 
    """Returns the string representation of word.
    
    >>> get_string(make_word_from_string('hello'))
    'hello'
    >>> get_string(make_word_from_list(['w', 'o', 'r', 'l', 'd']))
    'world'
    """
    "*** YOUR CODE HERE ***"
    return word[0]

def get_list(word):
    """Returns the list of characters representation of word.
    
    >>> get_list(make_word_from_string('hello'))
    ['h', 'e', 'l', 'l', 'o']
    >>> get_list(make_word_from_list(['w', 'o', 'r', 'l', 'd']))
    ['w', 'o', 'r', 'l', 'd']
    """
    "*** YOUR CODE HERE ***"
    character_list = []
    for i in word[0][:]: 
        character_list.append(i)
    return character_list

#helper functions 
def value_word_ADT(word): 
    return word[0]

def value_character_ADT(lst):
    return lst[:]


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

    in_common = 0 
    character_list = []

    for i in guess[0][:]:
        if i not in character_list: 
            character_list.append(i)

    for i in character_list: 
        if i in goal_word[0][:]: 
            in_common += 1 

    return in_common



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
    def guess(guess_word): 

        goal_list = get_list(goal_word)
        guess_list = get_list(guess_word)

        if not is_valid_guess(guess_word): 
            return not_a_word
        
        elif len(guess_list) != len(goal_list): 
            return bad_num_letters

        elif value_word_ADT(guess_list) == value_word_ADT(goal_list): 
            return win_message

        else: 
            return num_common_letters(goal_word, guess_word)
    
    return guess


import random 
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
    
    """strategy to solve problem: I generated random numbers to randomly make lists. 
    In all probability returns all combinations of number lists without consideration of order
    However, if the line:
    "if len(subset) == n and set(subset) not in set_subsets_list:"  (marked by asterisks)
    was: 
    "if len(subset) == n and subset not in subsets:" 
    unique lists would be kept, INCLUDING order 
    
    ***PLEASE NOTE that this code fails the last doctest because the order of subset elements, 
    the elements themselves are CORRECT***  

    Please look below active code for evidence of failed attempts at conventional strategies
    """
    subsets = [] 
    subset = []
    index = 0
    iterations = 10000
    #arbitrary number of iterations, as iterations increase, the probability of missing a subset will decrease
    while iterations > 0: 
        while index <= n: #if the order exceeds the list length, iterations decreases and creates a new random subset 
            new_num = lst[random.randint(0, len(lst) - 1)]
            set_subsets_list = [set(i) for i in subsets[:]] #use sets so that element order DOES NOT matter 
            if len(subset) == n and set(subset) not in set_subsets_list: #***
                subsets.append(subset)
                subset = [] 
                index = 0 
            elif new_num not in subset: 
                subset.append(new_num)
                index += 1     
        subset = [] 
        index = 0
        iterations -= 1 
    return subsets







    # number_of_list_indices = n 
    # indices_list = []
    # index = 0  
    # subsets = []
    # subset_list = []
    # #while len(indices_list) <= n-1:
    # #    indices_list.append(index)
    # #    index += 1 
    # indices_list = [x for x in range(n)][::-1]
    # #indices_list = [0,1,2,3, etc.] according to n 
    # #indices_list[::-1] = [4,3,2,1,0]
    # for i in indices_list: 
    #     subset_list.append(lst[i])
    # #subset_list = [lst[0], lst[1], lst[2], etc.]
    # subsets.append(subset_list)
    # for i in [x for x in range(n)][::-1]: # for i in [etc, 4, 3, 2, 1, 0]: 
    #     for h in lst[i:]: #start lst[4:] == lst[4] (the end), 3, 2, 1,  
    #         subset_list[i] = h
    #         subsets.append(subset_list)
    #         for j in subset_list[i:]:  #shifts left as indices decrease    
    #             subset_list[i] = j #record subset with one character changed 
    #             subsets.append(subset_list)
    #             print(subset_list)
    #             lst[i] = j
    # index_two = 0 
    # for i in subsets[index_two:]: 
    #     index_three = 0 
    #     for j in subsets[index_three+1:]: 
    #         if j is i: 
    #             subsets.remove(j)
    #     index_three += 1 
    #     index_two += 1 


    
    # import itertools
    # subset_list = []
    # for i in itertools.product():
    #     subset_list.append(i)









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
    #>>> mwfs, mwfl = make_word_from_string, make_word_from_list
    #>>> num_common_letters(mwfs('steal'), mwfs('least'))
    
    #
    "*** YOUR CODE HERE ***"
    mwfl = make_word_from_list
    if num_common_letters(mwfl(letter_list), guess) == score: 
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
    temp_word_list = get_list(word)
    subset_list = []
    score_counter = 0
    temp_subsets = possible_subsets
    for subset in temp_subsets[:]: 
        score_counter = 0 
        for character in temp_word_list: 
            if character in subset: 
                score_counter += 1 
        if score_counter == score: 
            subset_list.append(subset)
            
    
    return subset_list



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
    "*** YOUR CODE HERE ***"
    # this works in the python3 interpreter, but my doctest continues to error, 
    # raising "TypeError: 'function' object is not iterable" referring to 
    # " present, not_present = make_deductions(subsets, letters)" 

    present = []
    not_present = []
    for letter in letters: 
        temp_counter = 0 
        for subset in possible_subsets: 
            if letter in subset: 
                temp_counter += 1 
        if temp_counter == len(possible_subsets): 
            present.append(letter)
        elif temp_counter == 0: 
            not_present.append(letter)
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


