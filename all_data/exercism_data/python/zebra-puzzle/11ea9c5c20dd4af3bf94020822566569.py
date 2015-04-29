from enum import Enum, IntEnum
from itertools import product
from collections import defaultdict


class Color(Enum):
    red, green, ivory, yellow, blue = range(5)

class Number(IntEnum):
    first, second, third, fourth, fifth = range(1, 6)

class Pet(Enum):
    dog, snails, fox, horse, zebra = range(5)

class National(Enum):
    Englishman, Spaniard, Ukrainian, Norwegian, Japanese = range(5)

class Drink(Enum):
    coffee, tea, milk, orange_juice, water = range(5)

class Smoke(Enum):
    kool, chesterfield, lucky_strike, parliament, old_gold = range(5)


def possibilities():
    houses = defaultdict(list)
    for features in product(Number, National, Color, Pet, Drink, Smoke):
        house = {type(attr): attr for attr in features}
        if basic_check(house):
            houses[house[Number]].append(house)

    return houses

def basic_check(house):
    # 2. The Englishman lives in the red house.
    if (National.Englishman == house[National]) != (Color.red == house[Color]):
        return False
    # 3. The Spaniard owns the dog.
    if (National.Spaniard == house[National]) != (Pet.dog == house[Pet]):
        return False
    # 4. Coffee is drunk in the green house.
    if (Drink.coffee == house[Drink]) != (Color.green == house[Color]):
        return False
    # 5. The Ukrainian drinks tea.
    if (Drink.tea == house[Drink]) != (National.Ukrainian == house[National]):
        return False
    # 6. The green house is immediately to the right of the ivory house.
    # 7. The Old Gold smoker owns snails.
    if (Smoke.old_gold == house[Smoke]) != (Pet.snails == house[Pet]):
        return False
    # 8. Kools are smoked in the yellow house.
    if (Smoke.kool == house[Smoke]) != (Color.yellow == house[Color]):
        return False
    # 9. Milk is drunk in the middle house.
    if (Drink.milk == house[Drink]) != (Number.third == house[Number]):
        return False
    # 10. The Norwegian lives in the first house.
    if (National.Norwegian == house[National]) != (Number.first == house[Number]):
        return False
    # 11. The man who smokes Chesterfields lives in the house next to the man with the fox.
    # 12. Kools are smoked in the house next to the house where the horse is kept.
    # 13. The Lucky Strike smoker drinks orange juice.
    if (Smoke.lucky_strike == house[Smoke]) != (Drink.orange_juice == house[Drink]):
        return False
    # 14. The Japanese smokes Parliaments.
    if (Smoke.parliament == house[Smoke]) != (National.Japanese == house[National]):
        return False
    # 15. The Norwegian lives next to the blue house.
        
    return True

def bad_house(*houses):
    # 6. The green house is immediately to the right of the ivory house.
    if (Color.green == houses[-1][Color]) != (Color.ivory == houses[-2][Color]):
        return True
    return any(houses[-1][attr] == h[attr] for h in houses[:-1] for attr in houses[-1].keys())

def check_11_12_15(houses):
    # 11. The man who smokes Chesterfields lives in the house next to the man with the fox.
    # 12. Kools are smoked in the house next to the house where the horse is kept.
    # 15. The Norwegian lives next to the blue house.
    values = [Smoke.chesterfield, Smoke.kool, Pet.fox, Pet.horse, National.Norwegian, Color.blue]
    numbers = {value: house[Number] for house in houses for value in values
                                    if house[type(value)] == value}
    
    return abs(numbers[Smoke.chesterfield] - numbers[Pet.fox]) == 1 and \
           abs(numbers[Smoke.kool] - numbers[Pet.horse]) == 1 and \
           abs(numbers[National.Norwegian] - numbers[Color.blue]) == 1


def solution():
    all_houses = possibilities()

    results = []
    for house1 in all_houses[Number.first]:
        if Color.green == house1[Color]: # 6.
            continue
        for house2 in all_houses[Number.second]:
            if bad_house(house1, house2):
                continue
            for house3 in all_houses[Number.third]:
                if bad_house(house1, house2, house3):
                    continue
                for house4 in all_houses[Number.fourth]:
                    if bad_house(house1, house2, house3, house4):
                        continue
                    for house5 in all_houses[Number.fifth]:
                        if bad_house(house1, house2, house3, house4, house5):
                            continue

                        if check_11_12_15([house1, house2, house3, house4, house5]):
                            results.append([house1, house2, house3, house4, house5])

    

    return get_answer(results)[0]

def get_answer(results):
    questions = [Drink.water, Pet.zebra]
    answer_line = "It is the {0} who drinks the water.\nThe {1} keeps the zebra."
    answers = [{value: house[National] for house in houses for value in questions
                                       if house[type(value)] == value}
               for houses in results]
    return [answer_line.format(answer[Drink.water].name, answer[Pet.zebra].name) 
                 for answer in answers]
