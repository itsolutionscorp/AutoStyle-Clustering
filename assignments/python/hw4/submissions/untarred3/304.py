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


def make_word_from_string(s):
    """Creates an instance of the word ADT from the string s.
    """
    "*** YOUR CODE HERE ***"
    # word = s
    # return word
    # return 
    return s

    x = 0
    hold_list = []
    if type(s) == []:
        while x < len(s):
            hold_list = hold_list + s[x]
        return hold_list

    # else:
    #     return s

    

def make_word_from_list(lst):
    """Creates an instance of the word ADT from the list of characters lst.
    """
    "*** YOUR CODE HERE ***"
    # return lst #?
    # word = []
    # if lst == []:
    #     return []
    # else:
    #     for elements in lst:
    #         x = 0

    x = 0
    word = ''
    while x < len(lst):
        word = word + lst[x]
        x += 1
    return word 

    # the above returns a lst ['hello']
    # else:
    #     return [lst(0) + make_word_from_list(lst[1:])]
    # this is suppose to recursively add all the elements in the list
    # to make a one word.. for loop?
    

    # return [str(lst[0]) + str(lst[1:])]
    # return ''.join(lst) #this is cheating?
    # word = lst[0] + lst[1:]
    # return word
    
    #how to make a word from our list?

def get_string(word):
    """Returns the string representation of word.
    
    >>> get_string(make_word_from_string('hello'))
    'hello'
    >>> get_string(make_word_from_list(['w', 'o', 'r', 'l', 'd']))
    'world'
    """
    "*** YOUR CODE HERE ***"
    # return word
    # if type(word) == str:
    #     return make_word_from_string(word) #? just give yourself back the string? or data abstraction violation
    #     # return make_word_from_string(word)
    # else:
    #     make_word_from_list(word)
    # if type(word) == str:
    #     return word
    # else: 
    return word


    # how to take the word we have whether its a lst or string 
    # and return a string?


def get_list(word):
    """Returns the list of characters representation of word.
    
    >>> get_list(make_word_from_string('hello'))
    ['h', 'e', 'l', 'l', 'o']
    >>> get_list(make_word_from_list(['w', 'o', 'r', 'l', 'd']))
    ['w', 'o', 'r', 'l', 'd']
    """
    "*** YOUR CODE HERE ***"
    # string = make_word_from_string(word)
    # list_string = [string]
    # get_string turns something in to a string
    # lets take that string an turn it in to a 
     # else:
     #    return [list_string[0] + get
    # string_list = [word] 
    # string_list[0]
    # if type(word) == list:
    #     return word #?
    #     # return make_word_from_list(list)
    # else:
    #     return make_word_from_string(word)
    x = 0
    hold_list = []

    while x < len(word):
        hold_list = hold_list + [word[x]]
        x += 1
    return hold_list




    # make word form list returns to you a string, ['w', 'o', 'r', 'l', 'd']
    # that would return to you back 'world', so would it not be simpler to 
    # to just return the list?
    # with the make word from string function, you can turn a word into a string


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
    # mwfs, mwfl = make_word_from_string, make_word_from_list
    # x = 0 
    # word_list = get_list(goal_word)
    # guess_list = get_list(guess)
    # if goal_word == [] or goal_word == '':
    #     return x
    # elif word_list[0] == guess_list[0]:
    #     x += 1
    #     num_common_letters(word_list, guess_list[1:])
    

    # else:
    #     return num_common_letters(word_list(1:), guess_list[1:])
    # you're always going to get a word, so how can you compare letters in a 
    # word? it may be easier to compare lists?
    #counters dont work in recursion?
    # check if an element is in the list, if not add it then call lenght of list
    x = []
    for element1 in get_list(goal_word):
        for element2 in get_list(guess):
            if element1 == element2:
                if element1 not in x:
                    x = x + [element1]
    return len(x)
    
    # for element in get_list(goal_score):
    #     # while get_list(goal_score) and get_list(guess) != empty:
    #     # for element not in x:
    # if element is not in x:
    #     x = x + [element]
    # # if get_list(guess) != []:
    # for element in get_list(guess):
    #     if element is not in x:
    #         x = x + [element]
    # return len(x)







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
    # is_valid_guess(guess)
    # if len(goal_word) != len(guess):
    #     print(bad_num_letters)
    # elif goal_word == guess:
    #     print(win_message)
    # else:
    #     print(not_a_word)
    def helper(guess):
        if len(get_list(goal_word)) != len(get_list(guess)):
            return bad_num_letters
        elif goal_word == guess:
            return win_message
        elif not is_valid_guess(guess):
            return not_a_word 
        return num_common_letters(goal_word, guess)
    return helper

    



def insert_every(elem, lst):
    if lst == []:
        return []
    else:
        return [[elem] + lst[0]] + insert_every(elem, lst[1:])



def powerset(lst):
    if lst == []:
        return [[]]
    else:
        first, rest = lst[0], lst[1:]
        without_first = powerset(rest)
    return without_first + \
        insert_every(first, without_first)

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
    x = []
    for sublst in powerset(lst): 
        if len(sublst) == n:
            x = x + [sublst]
    return x



    #re-do subsets later, otherwise can't play game

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
    # num_common_letters(goal_word, guess)
    if len(guess) != len(letter_list):
        return False
    # x = []
    # for element1 in get_list(guess):
    #     for element2 in get_list(letter_list):
    #         if element1 == element2:
    #             if element1 not in x:
    #                 x = x + [element1]
    #                 if len(x) == score:
    #                     return True
    #                 else:
    #                     return False
    a = num_common_letters(guess, letter_list)
    if a == score:
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
    # if compatible(word, score, possible_subsets[0]):
    #     return
        # return num_common_letters(word, possible_subsets[0])
    # for elements in possible_subsets:
    #     if num_common_letters(get_list(word), elements) == score
    #         return elements
    x = []
    for element3 in possible_subsets:
        if compatible(word, score, element3):
            x = x + [element3]
    return x



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
    # """
    # present = []
    # not_present = []
    
    "*** YOUR CODE HERE ***"
    present = []
    not_present = []
    
    # for element4 in letters:
    #     toggle1, toggle2 = True, True
    #     for sublsts in possible_subsets:
    #         if element4 not in sublsts:
                
    #             toggle1 = False
    #         else:
                
    #             toggle2 = False
    #         if toggle1 == True:
    #             present += [element4]
    #         if toggle2 == True:
    #             not_present += [element4]
    # return present, not_present
    # # element4 not in present
    #its not working because, you're checking if the letter is in a sublst
    #what you're suppose to be checking is whether the letter is in all sublsts
    # and whether certain letters are not in a any sublst

    # for element4 in letters:
    #     for sublsts in possible_subsets:
    #         if element4 in sublsts and element4 not in present:
    #             present += [element4]
    #         elif element4 not in sublsts and element4 not in not_present:
    #             not_present += [element4]
    # return present, not_present
    x = []
    for element4 in letters:
        for sublsts in possible_subsets:
            if element4 in sublsts:
                x = x + [element4]


    # you want an element that is in all sublsts or not in all sublsts


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
