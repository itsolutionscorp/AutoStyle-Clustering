import copy

def solution():
    houses = [ { 'Nation' : ['Englishman', 'Spaniard', 'Ukrainian', 'Norwegian', 'Japanese'], 
                 'Colour' : ['Red', 'Green', 'Yellow', 'Blue', 'Ivory'], 
                 'Pet'    : ['Dog', 'Fox', 'Horse', 'Snails', 'Zebra'], 
                 'Drinks' : ['Coffee', 'Tea', 'Milk', 'Orange Juice', 'Water'], 
                 'Smokes' : ['Old Gold', 'Kools', 'Chesterfields', 'Lucky Strike', 'Parliaments'] }
                 for i in range(5) 
             ]

    rules = [ (does_match,   ('Nation', 'Englishman', 'Colour', 'Red')),
              (does_match,   ('Nation', 'Spaniard', 'Pet', 'Dog')),
              (does_match,   ('Drinks', 'Coffee', 'Colour', 'Green')),
              (does_match,   ('Nation', 'Ukrainian', 'Drinks', 'Tea')),
              (to_the_right, ('Colour', 'Green', 'Colour', 'Ivory')),
              (does_match,   ('Smokes', 'Old Gold', 'Pet', 'Snails')),
              (does_match,   ('Smokes', 'Kools', 'Colour', 'Yellow')),
              (at_position,  ('Drinks', 'Milk', 2)),
              (at_position,  ('Nation', 'Norwegian', 0)),
              (is_neighbour, ('Smokes', 'Chesterfields', 'Pet', 'Fox')),
              (is_neighbour, ('Smokes', 'Kools', 'Pet', 'Horse')),
              (does_match,   ('Smokes', 'Lucky Strike', 'Drinks', 'Orange Juice')),
              (does_match,   ('Nation', 'Japanese', 'Smokes', 'Parliaments')),
              (is_neighbour, ('Nation', 'Norwegian', 'Colour', 'Blue')),
              (all_unique,   ())
            ]

    if solve(houses, rules):
        water_drinker = [ house['Nation'] for house in houses 
                       if house['Drinks'] == ['Water']][0][0]
        zebra_keeper  = [ house['Nation'] for house in houses 
                       if house['Pet']    == ['Zebra']][0][0]

        solution_string = "It is the " + water_drinker + " who drinks the water.\n" +\
                          "The " + zebra_keeper + " keeps the zebra."

        print_solution(houses)
        return solution_string
    else:
        return "No solution found"


def solve(matrix, rules):
    while True:
        old_matrix = copy.deepcopy(matrix)
        for (rule, param) in rules:
            rule(matrix, *param)
        if old_matrix == matrix: break

    if any(len(v)>1 for m in matrix for v in m.values()):
        solve_recursive(matrix, rules)

    return all(len(v)==1 for m in matrix for v in m.values() )

def solve_recursive(matrix, rules):
    for m in range(len(matrix)):
        for k in matrix[m].keys():
            if len(matrix[m][k]) > 1:
                removeitems = []
                for i in matrix[m][k]:
                    testmatrix = copy.deepcopy(matrix)
                    testmatrix[m][k] = [i]
                    if not solve(testmatrix, rules):
                        removeitems.append(i)
                for i in removeitems:
                    matrix[m][k].remove(i)

def print_solution(matrix):
    header = ['Field'] + [k for k in matrix[0].keys() ]
    values = [ [v for [v] in m.values() ] for m in matrix ]
    header_widths = [ len(k) for k in header ]
    value_widths = [ [len(v) for v in value ] for value in values ]
    widths = [5] + [ max(*w) for w in zip(header_widths, *value_widths)]

    separator = '+' + '+'.join('-'*(w+2) for w in widths) + '+'
    linegen = lambda l, w: '|' + '|'.join(' '+l[i].ljust(w[i]+1) for i in range(len(l)))+'|'

    line = linegen(header, widths)
    print separator
    print line
    print separator

    for i in range(len(matrix)):
        line = linegen([str(i)] + values[i], widths)
        print line
    print separator

def does_match(matrix, k1, v1, k2, v2):
    for m in matrix:
        if not (v1 in m[k1] and v2 in m[k2]):
            if v1 in m[k1]: m[k1].remove(v1)
            if v2 in m[k2]: m[k2].remove(v2)
        if m[k1] == [v1]: m[k2] = [v2]
        if m[k2] == [v2]: m[k1] = [v1]

def at_position(matrix, key, value, position):
    matrix[position][key] = [value]

def to_the_right(matrix, k1, v1, k2, v2):
    l = len(matrix)-1
    if v1 in matrix[0][k1]: matrix[0][k1].remove(v1)
    if v2 in matrix[l][k2]: matrix[l][k2].remove(v2)
    for i in range(l):
        if not ((v1 in matrix[i+1][k1]) and (v2 in matrix[i][k2])):
            if v1 in matrix[i+1][k1]: matrix[i+1][k1].remove(v1)
            if v2 in matrix[i  ][k2]: matrix[i  ][k2].remove(v2)
        if matrix[i+1][k1] == [v1]: matrix[i  ][k2] = [v2]
        if matrix[i  ][k2] == [v2]: matrix[i+1][k1] = [v1]

def is_neighbour(matrix, k1, v1, k2, v2):
    l = len(matrix)-1
    if (v1 in matrix[0][k1]) and (v2 not in matrix[  1][k2]): matrix[0][k1].remove(v1)
    if (v2 in matrix[0][k2]) and (v1 not in matrix[  1][k1]): matrix[0][k2].remove(v2)
    if (v1 in matrix[l][k1]) and (v2 not in matrix[l-1][k2]): matrix[l][k1].remove(v1)
    if (v2 in matrix[l][k2]) and (v1 not in matrix[l-1][k1]): matrix[l][k2].remove(v2)

    for i in range(1,l):
        if not ((v1 in matrix[i][k1]) and ((v2 in matrix[i-1][k2]) or (v2 in matrix[i+1][k2]))):
            if v1 in matrix[i  ][k1]: matrix[i  ][k1].remove(v1)
        if not ((v2 in matrix[i][k2]) and ((v1 in matrix[i-1][k1]) or (v1 in matrix[i+1][k1]))):
            if v2 in matrix[i  ][k2]: matrix[i  ][k2].remove(v2)
        if matrix[i][k1] == [v1] and ((v2 in matrix[i-1][k2]) != (v2 in matrix[i+1][k2])):
            if v2 in matrix[i-1][k2]: matrix[i-1][k2] = [v2]
            if v2 in matrix[i+1][k2]: matrix[i+1][k2] = [v2]
        if matrix[i][k2] == [v2] and ((v1 in matrix[i-1][k1]) != (v1 in matrix[i+1][k1])):
            if v1 in matrix[i-1][k1]: matrix[i-1][k1] = [v1]
            if v1 in matrix[i+1][k1]: matrix[i+1][k1] = [v1]

def all_unique(matrix):
    keys = matrix[0].keys()
    for k in keys:
        for i in range(len(matrix)):
            l = [set(m[k]) for m in matrix]
            p = l.pop(i)
            v = p.difference(*l)
            if len(v) == 1:
                matrix[i][k] = [v.pop()]
        for i in range(len(matrix)):
            p = matrix[i][k]
            if len(p) == 1:
                for j in range(len(matrix)):
                    if j != i and p[0] in matrix[j][k]:
                        matrix[j][k].remove(p[0])

if __name__ == "__main__":

    houses = [ { 'Nation' : ['Spaniard', 'Ukrainian', 'Norwegian'], 
                 'Pet'    : ['Dog', 'Fox', 'Zebra'], 
                 'Drinks' : ['Tea', 'Milk', 'Orange Juice'] } 
               for i in range(3) ]
               
    rules = [ (at_position, ('Drinks', 'Milk', 1)),
              (does_match,  ('Nation', 'Spaniard', 'Pet', 'Dog')),
              (does_match,  ('Nation', 'Ukrainian', 'Drinks', 'Tea')),
              (at_position, ('Nation', 'Norwegian', 0)),
              (does_match,  ('Drinks', 'Orange Juice', 'Pet', 'Fox')),
              (all_unique,  ())
            ]

    print "Simplified Zebra Puzzle solution:"
    if solve(houses, rules):
        print_solution(houses)
    else:
        print "No solution found"
    print
    
    print "Original Zebra Puzzle solution:"
    solution()
