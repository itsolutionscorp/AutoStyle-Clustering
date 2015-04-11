"exer who has the zebra"""

# goal: reduce the number of variants possible, roughly O(n) and then with
# those that # remain, using brute force by iterating over remaining to
# find the subset that satisfies all constraints. O(n^num dimensions-1)
#
# There are two major types of constraints in this problem.
# Simple - applying the constraint filters out those failing in a single pass
# Relative - these filter based on the remaining possibilities. If the
# search space changes, then these constraints must be re-applied

from collections import namedtuple, Counter, defaultdict
from itertools import product, islice


def grouper(iterable, grp_size):
    """
    split iterable in to groups of size grp_size
    :param iterable:  the thing to split into groups
    :param grp_size:  the size of the group to make
    :return: :rtype:  tuple
    """
    itr = iter(iterable)
    while True:
        chunk = tuple(islice(itr, grp_size))
        if not chunk:
            return
        yield chunk


class Problem(object):
    """a constraint solver"""

    def __init__(self, dimensional_data):
        """dimensional_data is a list of (dim_name, dim_values) to be used
        :param dimensional_data:
        """
        self.universes = []
        self.dimensions = dimensional_data
        self.dimension_names = [name for (name, _) in dimensional_data]
        self._variant = namedtuple('Variant', self.dimension_names)
        for vdata in product(*[vals for (name, vals) in dimensional_data]):
            self.universes.append(self._variant._make(vdata))
        self.universes_len = len(self.universes)
        self.next_to_args = []
        self.right_of_args = []

    def _guard_args(self, args):
        '''raise error if dname or dvalue does not exist
        :param args: 0 or more pairs of dname, dvalue arguments
        '''
        for dname, dvalue in grouper(args, 2):
            if dname not in self.dimension_names:
                raise ValueError('invalid dimension name: %s' % dname)

            for (name, values) in self.dimensions:
                if name == dname:
                    if dvalue in values:
                        return
            raise ValueError(
                'Dimension {0:s} has no value: {1:s}'.format(dname, dvalue))

    @staticmethod
    def both_or_neither(vrnt, args):
        """both conditions are true or neither are true
        :param vrnt: variant record to evaluate
        :param args: a pair of domain name, value to use for the comparison
        """
        # XOR (^)
        # a  b  output
        # == == ====
        # 0  0  0
        # 1  0  1
        # 0  1  1
        # 1  1  0
        dname1, con1, dname2, con2 = args
        return (getattr(vrnt, dname1) == con1) ^ (
            getattr(vrnt, dname2) == con2)

    @staticmethod
    def not_both(vrnt, args):
        '''both conditions are true'''
        dname1, con1, dname2, con2 = args
        return (getattr(vrnt, dname1) == con1) & (
            getattr(vrnt, dname2) == con2)

    @property
    def has_changed(self):
        '''has the number of variants in self.universes changed since last
        checked?'''
        current = len(self.universes)
        _has_changed = self.universes_len != current
        self.universes_len = current
        return _has_changed

    def reapply_relative(self):
        """when things change, relative constraints need to be reapplied
        to see if more  variants are filtered out"""
        while self.has_changed:
            self.next_to()
            self.right_of()

    def next_to(self, args=None):
        """keeps track of relative location args that should be
        reapplied as the universes change."""
        if args is not None:
            self._guard_args(args)
            self.next_to_args.append(args)
            # if next_to then both can't occur at the same location
            self.constraint(self.not_both, args)
            self._next_to(args)
        else:
            for args in self.next_to_args:
                self._next_to(args)

    def _next_to(self, args):
        """
        do the actual work of implementing the next_to constraint
        :param args:
        """
        dname1, con1, dname2, con2 = args
        locations = []
        for vrnt in self.universes:
            if getattr(vrnt, dname1) == con1:
                locations.append(vrnt.house - 1)
                locations.append(vrnt.house + 1)
        # filter possible locations down to legal locations 0-4
        num_remaining_dims = len(self.dimension_names) - 1
        locations = set(
            [x for x in set(locations) if x in range(num_remaining_dims)])

        _universes = []
        for variant in self.universes:
            if getattr(variant, 'house') not in locations:
                if getattr(variant, dname2) == con2:
                    continue
            else:
                if len(locations) == 1:
                    if getattr(variant, dname2) != con2:
                        continue

            _universes.append(variant)
        self.universes = _universes
        self.reapply_relative()

    def right_of(self, args=None):
        """keep track of  args for this relative constraint, since they
        will need to be rechecked after other constraints are applied"""
        if args is not None:
            self._guard_args(args)
            self.right_of_args.append(args)
            # # optimize - if right of, then d2/c2 can't occur for the first
            dname1, con1, dname2, con2 = args
            self.constraint(self.not_both, (dname2, con2, 'house', 0))
            # and d1/c1 can't occur for the last house
            self.constraint(self.not_both, (dname1, con1, 'house', 4))
            # if right_of then both can't occur at the same location
            self.constraint(self.not_both, args)
            self._right_of(args)
        else:
            for args in self.right_of_args:
                self._right_of(args)

    def _right_of(self, args):
        """
        implement the right_of constraint logic for args
        :param args:
        """
        dname1, con1, dname2, con2 = args

        # potential locations for con1 are 0-3
        locations = [vrnt.house + 1 for vrnt in self.universes
                     if getattr(vrnt, dname1) == con1]
        locations = set(locations)

        _universes = []
        for variant in self.universes:
            if getattr(variant, 'house') not in locations:
                if getattr(variant, dname2) == con2:
                    continue
            else:
                if len(locations) == 1:
                    if getattr(variant, dname2) != con2:
                        continue

            _universes.append(variant)

        self.universes = _universes
        self.reapply_relative()

    def list(self, view=None):
        """prints out variants"""
        if view is None:
            view = self.universes
        for variant in view:
            print variant

    def display(self, view=None):
        """print out current variant stats"""
        counters = {name: Counter() for name in self.dimension_names}
        if view is None:
            view = self.universes
        for variant in view:
            for name in self.dimension_names:
                counters[name][getattr(variant, name)] += 1
        print "Universe contains %i varients" % len(self.universes)
        for name in self.dimension_names:
            print "\t", name, counters[name]

    def stats(self):
        """display counters for values of all dimensions"""
        for name, values in self.dimensions:
            for value in values:
                print "%s:%s" % (name, value)
                self.display(view=self.which(name, value))

    def constraint(self, constraint_fn, args):
        """filter constraints (con1, con2), are met, or filter if specifically
         not possible"""
        self._guard_args(args)
        _universes = []
        for variant in self.universes:
            if constraint_fn(variant, args):
                continue
            _universes.append(variant)

        self.universes = _universes
        self.reapply_relative()

    def which(self, dname, dvalue):
        """return an iterable of those variants that have the value for the
        dimension"""
        self._guard_args((dname, dvalue))
        for variant in self.universes:
            if getattr(variant, dname) == dvalue:
                yield variant

    def varies_on(self):
        """using 'house' dimension as the pivot, count the variant
        possibilities that remain for each of the remaining dimensions and
        yields the information and the dimension values for each dimension"""
        for name, values in self.dimensions:
            if name == 'house':
                house_nums = values
                break
        other_dims = [name for name in self.dimension_names if
                      name != 'house']

        for house_num in house_nums:
            counters = {name: Counter() for name in other_dims}
            for variant in self.which('house', house_num):
                for name in other_dims:
                    counters[name][getattr(variant, name)] += 1

            for name in other_dims:
                yield house_num, name, counters[name].keys()

    @property
    def solved(self):
        """return True if solved conditions are met: all dimensions have
        all values accounted for within the 5 variant recs"""
        _solved = False
        # a solution will consist of 5 variants, if we vary on house
        num_remaining_dims = len(self.dimension_names) - 1
        if len(self.universes) == num_remaining_dims:
            dvals = defaultdict(list)
            for _, name, dvalues in self.varies_on():
                dvals[name] += dvalues
            _solved = True
            # and all 5 of those variants will account for all dimensions
            # and their values
            other_dims = [name for name in self.dimension_names if
                          name != 'house']
            for name in other_dims:
                if len(set(dvals[name])) != num_remaining_dims:
                    _solved = False
                    break

        return _solved

    def solve(self):
        """once all constraints are in place, process remaining variants to
        find if one of the solves all constraints"""
        # list of variants segmented by house
        num_remaining_dims = len(self.dimension_names) - 1
        pools = [list(self.which('house', i)) for i in
                 range(num_remaining_dims)]
        # iterate over remaining variants selecting 1 variant from each pool
        for self.universes in product(*pools):
            for house, dname, values in self.varies_on():
                for dvalue in values:
                    # treat each dimension in permutation as a new constraint
                    con_args = ('house', house, dname, dvalue)
                    self.constraint(self.both_or_neither, con_args)

                    if self.solved:
                        # for house, dname, values in self.varies_on():
                        #     print house, dname, values
                        return True  # found it
        # have not found a permutation that satisfies constraints
        return False


def solution(display=False):
    """who drinks water and who has the zebra"""
    dimensions = (
        ('house', range(5)),
        ('resident', ['Englishman', 'Spaniard', 'Ukrainian', 'Norwegian',
                      'Japanese']),
        ('color', ['Red', 'Green', 'Ivory', 'Blue', 'Yellow']),
        ('pet', ['dog', 'fox', 'horse', 'snails', 'zebra']),
        ('drinks', ['coffee', 'tea', 'milk', 'orange juice', 'water']),
        ('smokes', ['Old Gold', 'Kools', 'Chesterfields', 'Lucky Strike',
                    'Parliments']),
    )
    problem = Problem(dimensions)
    if display:
        problem.display()

    # 2. The Englishman lives in the red house.
    con_args = ('resident', 'Englishman', 'color', 'Red')
    problem.constraint(problem.both_or_neither, con_args)

    # 3. The Spaniard owns the dog.
    con_args = ('resident', 'Spaniard', 'pet', 'dog')
    problem.constraint(problem.both_or_neither, con_args)

    # 4. Coffee is drunk in the green house.
    con_args = ('drinks', 'coffee', 'color', 'Green')
    problem.constraint(problem.both_or_neither, con_args)

    # 5. The Ukrainian drinks tea.
    con_args = ('resident', 'Ukrainian', 'drinks', 'tea')
    problem.constraint(problem.both_or_neither, con_args)

    # 6. The green house is immediately to the right of the ivory house.
    con_args = ('color', 'Ivory', 'color', 'Green')
    problem.right_of(con_args)

    # 7. The Old Gold smoker owns snails.
    con_args = ('smokes', 'Old Gold', 'pet', 'snails')
    problem.constraint(problem.both_or_neither, con_args)

    # 8. Kools are smoked in the yellow house.
    con_args = ('smokes', 'Kools', 'color', 'Yellow')
    problem.constraint(problem.both_or_neither, con_args)

    # 9. Milk is drunk in the middle house. 0,1,(2),3,4
    con_args = ('house', 2, 'drinks', 'milk')
    problem.constraint(problem.both_or_neither, con_args)

    # 10. The Norwegian lives in the first house. (0),1,2,3,4
    con_args = ('house', 0, 'resident', 'Norwegian')
    problem.constraint(problem.both_or_neither, con_args)

    # 11. The man who smokes Chesterfields lives in the house next to the
    #     man with the fox.
    con_args = ('smokes', 'Chesterfields', 'pet', 'fox')
    problem.next_to(con_args)

    # 12. Kools are smoked in the house next to the house where the horse
    #     is kept.
    con_args = ('smokes', 'Kools', 'pet', 'horse')
    problem.next_to(con_args)

    # 13. The Lucky Strike smoker drinks orange juice.
    con_args = ('smokes', 'Lucky Strike', 'drinks', 'orange juice')
    problem.constraint(problem.both_or_neither, con_args)

    # 14. The Japanese smokes Parliaments.
    con_args = ('resident', 'Japanese', 'smokes', 'Parliments')
    problem.constraint(problem.both_or_neither, con_args)

    # 15. The Norwegian lives next to the blue house.
    con_args = ('resident', 'Norwegian', 'color', 'Blue')
    problem.next_to(con_args)

    if display:
        print "Exploring remaining variants for Constraint Satisfaction"
        problem.display()

    if problem.solve():
        if display:
            print "Success"
            problem.display()
            problem.list()
        phrase = "It is the %s who drinks the water.\nThe %s keeps the zebra."
        return phrase % (list(problem.which('drinks', 'water'))[0].resident,
                         list(problem.which('pet', 'zebra'))[0].resident)

    else:
        return 'Failed'


def main():
    '''run problem without test harness and provide some feedback'''
    print solution(display=True)


if __name__ == '__main__':
    main()
