from enum import Enum
from itertools import permutations


class HouseColor(Enum):
    Red = 1
    Yellow = 2
    Green = 3
    Blue = 4
    Ivory = 5


class Cigars(Enum):
    LuckyStrikes = 1
    Parliaments = 2
    Kools = 3
    Chesterfields = 4
    OldGolds = 5


class Animals(Enum):
    Dog = 1
    Snails = 2
    Fox = 3
    Horse = 4
    Zebra = 5


class Drinks(Enum):
    Coffee = 1
    Tea = 2
    OrangeJuice = 3
    Milk = 4
    Water = 5


class National(Enum):
    English = 1
    Norwegian = 2
    Japanese = 3
    Spaniard = 4
    Ukranian = 5

class House:
    def __init__(self):
        self.color = HouseColor.Undefined
        self.cigars = Cigars.Undefined
        self.animal = Animals.Undefined
        self.drinks = Drinks.Undefined
        self.national = National.Undefined





def solution():
    for nat in permutations(National):
        for dri in permutations(Drinks):
            for ani in permutations(Animals):
                for cig in permutations(Cigars):
                    for col in permutations(HouseColor):
                        #2. The Englishman lives in the red house.
                        if col[list(nat).index(National.English)] != HouseColor.Red:
                            continue

                        #3. The Spaniard owns the dog.
                        if ani[list(nat).index(National.Spaniard)] != Animals.Dog:
                            continue

                        #4. Coffee is drunk in the green house.
                        if dri[list(col).index(HouseColor.Green)] != Drinks.Coffee:
                            continue

                        #5. The Ukrainian drinks tea.
                        if dri[list(nat).index(National.Ukranian)] != Drinks.Tea:
                            continue

                        #6. The green house is immediately to the right of the ivory house.
                        if abs(list(col).index(HouseColor.Green) - list(col).index(HouseColor.Ivory)) != 1:
                            continue

                        #7. The Old Gold smoker owns snails.
                        if ani[list(cig).index(Cigars.OldGolds)] != Animals.Snails:
                            continue

                        #8. Kools are smoked in the yellow house.
                        if cig[list(col).index(HouseColor.Yellow)] != Cigars.Kools:
                            continue

                        #9. Milk is drunk in the middle house.
                        if list(dri).index(Drinks.Milk) != 2:
                            continue

                        #10. The Norwegian lives in the first house.
                        if list(nat).index(National.Norwegian) != 0:
                            continue

                        #11. The man who smokes Chesterfields lives in the house next to the man with the fox.
                        if abs(list(cig).index(Cigars.Chesterfields) - list(ani).index(Animals.Fox)) != 1:
                            continue

                        #12. Kools are smoked in the house next to the house where the horse is kept.
                        if abs(list(cig).index(Cigars.Kools) - list(ani).index(Animals.Horse)) != 1:
                            continue

                        #13. The Lucky Strike smoker drinks orange juice.
                        if dri[list(cig).index(Cigars.LuckyStrikes)] != Drinks.OrangeJuice:
                            continue

                        #14. The Japanese smokes Parliaments.
                        if cig[list(nat).index(National.Japanese)] != Cigars.Parliaments:
                            continue

                        #15. The Norwegian lives next to the blue house.
                        if abs(list(nat).index(National.Norwegian) - list(col).index(HouseColor.Blue)) != 1:
                            continue

                        #We found it !!!
                        nwater = nat[list(dri).index(Drinks.Water)]
                        nzebra = nat[list(ani).index(Animals.Zebra)]
                        return "It is the " + nwater + " who drinks the water.\nThe " + nzebra + " keeps the zebra."
    return ''
