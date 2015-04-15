__author__ = 'nebur1989'

from string import Template

verse_template = Template(
    "$bottles of beer on the wall, $bottles of beer.\n"
    "$action_one and $action_two, $bottles_after of beer on the wall.\n"
)

action_go_to_store = "Go to the store"
action_buy_more = "buy some more"
action_take_it = "Take it down"
action_take_one = "Take one down"
action_pass_it = "pass it around"


def get_bottles(num):
    bottles = str(num) + " bottles"
    if num == 0:
        bottles = "no more bottles"
    elif num == 1:
        bottles = "1 bottle"
    return bottles

def verse(num):
    action_one = action_take_one
    action_two = action_pass_it
    if num == 0:
        action_one = action_go_to_store
        action_two = action_buy_more
    elif num == 1:
        action_one = action_take_it
        action_two = action_pass_it

    num_after = (num - 1) % 100

    verse_res = verse_template.substitute(bottles=get_bottles(num), bottles_after=get_bottles(num_after),
                                          action_one=action_one, action_two=action_two)

    return verse_res[:1].upper() + verse_res[1:]


def song(init, end=0):
    verse_res = ""
    for num in reversed(range(end, init + 1)):
        verse_res += verse(num) + "\n"
    return verse_res
