"""Solves the Zebra puzzle."""
from itertools import permutations


def solution():
    result = solve_puzzle()
    water_drinker = result.get("Beverage", "Water")["Person"]
    zebra_owner = result.get("Mutt", "Zebra")["Person"]
    return "It is the %s who drinks the water.\n" % water_drinker + \
           "The %s keeps the zebra." % zebra_owner


def solve_puzzle():
    properties = create_properties()
    ruleset = create_ruleset()
    return Resolver(properties, ruleset).find_solution()


def create_properties():
    return [
        Properties("Person", (
            "Englishman",
            "Spaniard",
            "Ukranian",
            "Japanese",
            "Norwegian"
        )),
        Properties("Color", (
            "Red",
            "Green",
            "Ivory",
            "Yellow",
            "Blue"
        )),
        Properties("Beverage", (
            "Coffee",
            "Tea",
            "Milk",
            "Orange Juice",
            "Water"
        )),
        Properties("Smoke", (
            "OldGold",
            "Kools",
            "Chesterfields",
            "Lucky Strike",
            "Parliaments"
        )),
        Properties("Mutt", (
            "Dog",
            "Snails",
            "Fox",
            "Horse",
            "Zebra"
        ))
    ]


def create_ruleset():
    return [
        The.Englishman.lives_in(The.Red.house),
        The.Spaniard.owns(The.Dog),
        Coffee.is_drunk_in(The.Green.house),
        The.Ukranian.drinks(Tea),
        The.Green.house.is_to_the_right_of(The.Ivory.house),
        The.OldGold.smoker.owns(Snails),
        Kools.are_smoked_in(The.Yellow.house),
        Milk.is_drunk_in_the_middle_house(),
        The.Norwegian.lives_in_the_first_house(),
        Chesterfields.are_smoked_next_to(The.Fox),
        Kools.are_smoked_next_to(The.Horse),
        The.LuckyStrike.smoker.drinks(OrangeJuice),
        The.Japanese.smokes(Parliaments),
        The.Norwegian.lives_next_to(The.Blue.house)
    ]


class The(object):
    """This class is used to write (freakishly) readable rules.
    When accessing 'The.thing', the global variable 'thing' is returned.
    """
    def __getattr__(self, key):
        return globals()[key]

The = The()


class Property(object):
    """Describes a property for the puzzle and is used to produce
    rules for that property.
    """
    def __init__(self, name):
        self.name = name
        self.reset()

    def reset(self):
        self.house_nr = None

    def bind_to_house(self, house_nr):
        self.house_nr = house_nr

    def is_bound_to_house(self):
        return self.house_nr is not None

    def is_in_the_first_house(self):
        def rule():
            if self.is_bound_to_house():
                return self.house_nr == 1
        return rule

    def is_in_the_middle_house(self):
        def rule():
            if self.is_bound_to_house():
                return self.house_nr == 3
        return rule

    def is_next_to(self, other):
        def rule():
            if self.is_bound_to_house() and other.is_bound_to_house():
                return abs(self.house_nr - other.house_nr) == 1
        return rule

    def is_to_the_right_of(self, other):
        def rule():
            if self.is_bound_to_house() and other.is_bound_to_house():
                return self.house_nr == other.house_nr + 1
        return rule

    def is_same_house_as(self, other):
        def rule():
            if self.is_bound_to_house() and other.is_bound_to_house():
                return self.house_nr == other.house_nr
        return rule

    def __getattr__(self, attribute):
        """Provide access to virtual properties and methods
        that are used for writing readable rules.
        """
        if attribute in ('house', 'smoker'):
            return self
        if attribute.endswith("to_the_right_of"):
            return self.is_to_the_right_of
        if attribute.endswith("middle_house"):
            return self.is_in_the_middle_house
        if attribute.endswith("first_house"):
            return self.is_in_the_first_house
        if attribute.endswith("next_to"):
            return self.is_next_to
        return self.is_same_house_as


class Properties(list):
    """A collection of properties of a given type (persons, colors, etc.)."""
    def __init__(self, property_type, names):
        super(Properties, self).__init__()
        self.property_type = property_type
        for name in names:
            p = Property(name)
            self.append(p)
            # Make the property available as a global variable, which
            # can be used for writing the rules in a really clear way.
            globals()[name.replace(' ', '')] = p

    def reset(self):
        for property_ in self:
            property_.reset()


class Resolver(object):
    """Resolves the ruleset puzzle solution."""
    def __init__(self, properties, ruleset):
        self.properties = properties
        self.ruleset = ruleset

    houses_permutations = list(permutations([1, 2, 3, 4, 5]))

    def find_solution(self, idx=0):
        """Solves the puzzle by recursively searching for property
        permutations for which the ruleset is satisfied, until
        satisfying permutations are found for all sets of properties.
        """
        properties = self.properties[idx]
        for houses_permutation in self.houses_permutations:
            for property_, house in zip(properties, houses_permutation):
                property_.bind_to_house(house)
            if not any(rule() is False for rule in self.ruleset):
                # No more property sets. We found the solution!
                if idx + 1 == len(self.properties):
                    return Solution(self.properties)
                # Recurse into the next property set.
                result = self.find_solution(idx + 1)
                if result:
                    return result
        properties.reset()


class Solution(dict):
    """Describes the solution for the puzzle."""
    def __init__(self, properties):
        super(Solution, self).__init__()
        for house_nr in range(1, 6):
            self[house_nr] = {"House": house_nr}
        for p in properties:
            for property_ in p:
                self[property_.house_nr][p.property_type] = property_.name

    def get(self, property_type, name):
        """Returns a dict containing all data for the house that relates
        to the provided property type and name.
        """
        return next(
            data for house_nr, data in self.items()
            if data[property_type] == name
        )
