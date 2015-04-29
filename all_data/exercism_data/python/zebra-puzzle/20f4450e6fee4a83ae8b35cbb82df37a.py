"""Solves the Zebra puzzle."""
from itertools import permutations
from collections import OrderedDict


__all__ = ['solution', 'print_full_solution']


def solution():
    """Return the solution for the Zebra puzzle's final questions:
    Which of the residents drinks water?  Who owns the zebra?
    """
    result = solve_puzzle()
    water_drinker = result.get(Water)["Resident"]
    zebra_owner = result.get(Zebra)["Resident"]
    return "It is the %s who drinks the water.\n" % water_drinker + \
           "The %s keeps the zebra." % zebra_owner


def print_full_solution():
    """Print a table containing the full solution for the Zebra puzzle."""
    result = solve_puzzle()
    field_names = result[1].keys()
    columns = [
        [field_name] + [record[field_name] for record in result.values()]
        for field_name in field_names
    ]
    widths = [max(len(str(v)) for v in values) for values in columns ]
    row_format = "| " + " | ".join("%-{0}s".format(w) for w in widths) + " |"
    separator = "+-" + "-+-".join("-" * w for w in widths) + "-+"
    print(separator)
    for idx, row in enumerate(zip(*columns)):
        print(row_format % row)
        if idx == 0:
            print(separator)
    print(separator)


def solve_puzzle():
    """Solve the puzzle and return a data structure representing the solution."""
    attributes = create_attributes()
    constraints = create_constraints()
    return Resolver(attributes, constraints).find_solution()


def create_attributes():
    return [
        Attributes("Resident", (
            "Englishman",
            "Spaniard",
            "Ukranian",
            "Japanese",
            "Norwegian"
        )),
        Attributes("Color", (
            "Red",
            "Green",
            "Ivory",
            "Yellow",
            "Blue"
        )),
        Attributes("Beverage", (
            "Coffee",
            "Tea",
            "Milk",
            "Orange Juice",
            "Water"
        )),
        Attributes("Brand of Cigarettes", (
            "OldGold",
            "Kools",
            "Chesterfields",
            "Lucky Strike",
            "Parliaments"
        )),
        Attributes("Mutt", (
            "Dog",
            "Snails",
            "Fox",
            "Horse",
            "Zebra"
        ))
    ]


def create_constraints():
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
    """This class is used to write (freakishly) readable constraints.
    When accessing 'The.thing', the global variable 'thing' is returned.
    """
    def __getattr__(self, key):
        return globals()[key]

The = The()


class Attribute(object):
    """Describes an attribute for the puzzle and is used to produce
    constraints for that attribute.
    """
    def __init__(self, attribute_type, name):
        self.attribute_type = attribute_type
        self.name = name
        self.reset()

    def reset(self):
        self.house_nr = None

    def bind(self, house_nr):
        self.house_nr = house_nr

    def is_bound(self):
        return self.house_nr is not None

    def is_in_the_first_house(self):
        def constraint():
            if self.is_bound():
                return self.house_nr == 1
        return constraint

    def is_in_the_middle_house(self):
        def constraint():
            if self.is_bound():
                return self.house_nr == 3
        return constraint

    def is_next_to(self, other):
        def constraint():
            if self.is_bound() and other.is_bound():
                return abs(self.house_nr - other.house_nr) == 1
        return constraint

    def is_to_the_right_of(self, other):
        def constraint():
            if self.is_bound() and other.is_bound():
                return self.house_nr == other.house_nr + 1
        return constraint

    def is_same_house_as(self, other):
        def constraint():
            if self.is_bound() and other.is_bound():
                return self.house_nr == other.house_nr
        return constraint

    def __getattr__(self, attribute):
        """Provide access to virtual attributes and methods
        that are used for writing (freakishly) readable constraints.
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


class Attributes(list):
    """A collection of attributes of a given type (residents, colors, etc.)."""
    def __init__(self, attribute_type, names):
        super(Attributes, self).__init__()
        self.attribute_type = attribute_type
        for name in names:
            p = Attribute(attribute_type, name)
            self.append(p)
            # Make the attribute available as a global variable, which
            # can be used for writing the constraints in a really clear way.
            globals()[name.replace(' ', '')] = p

    def reset(self):
        for attribute in self:
            attribute.reset()


class Resolver(object):
    """Resolves the puzzle using the provided attributes and constraints."""
    def __init__(self, attributes, constraints):
        self.attributes = attributes
        self.constraints = constraints

    houses_permutations = list(permutations([1, 2, 3, 4, 5]))

    def find_solution(self):
        """Find and return the solution for the puzzle.
        When not exactly one solution is found, then an exception is raised.
        """
        solutions = list(self.find_solutions())
        if not solutions:
            raise NoSolutionError()
        if len(solutions) > 1:
            raise NoSingleSolutionError()
        return solutions[0]

    def find_solutions(self, idx=0):
        """Find all solutions for the puzzle by recursively searching for
        attribute permutations for which all constraints are satisfied.
        """
        attributes = self.attributes[idx]
        for houses_permutation in self.houses_permutations:
            for attribute, house in zip(attributes, houses_permutation):
                attribute.bind(house)
            if not any(constraint() is False for constraint in self.constraints):
                # No more sets of attributes left. We found the solution!
                if idx + 1 == len(self.attributes):
                    yield Solution(self.attributes)
                else:
                    # Recurse into the next set of attributes.
                    for solution in self.find_solutions(idx + 1):
                        yield solution
        attributes.reset()


class NoSolutionError(Exception):
    """Raised when the resolver is unable to find any solution for the puzzle."""
    pass


class NoSingleSolutionError(Exception):
    """Raised when the resolver finds multiple solutions for the puzzle."""
    pass


class Solution(OrderedDict):
    """Describes a solution for the puzzle."""
    def __init__(self, attributes):
        super(Solution, self).__init__()
        for house_nr in range(1, 6):
            self[house_nr] = OrderedDict({"House": house_nr})
        for p in attributes:
            for attribute in p:
                self[attribute.house_nr][p.attribute_type] = attribute.name

    def get(self, attribute):
        """Return data for the house that relates to the provided attribute."""
        return next(
            data for house_nr, data in self.items()
            if data[attribute.attribute_type] == attribute.name
        )
