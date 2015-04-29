from itertools import permutations

def right(a, b):
    return a - b == 1
    
def adjacent(a, b):
    return abs(a - b) == 1
    
def solution():
    #creates lists of permutations based upon 5 houses and given conditions
    houses = first, second, third, fourth, fifth = range(1,6)
    orderings = list(permutations(houses))
     
    result = next(#return nationality for water, zebra given conditions below
                   [
                    {England:"Englishman",
                     Spain:"Spaniard",
                     Ukraine:"Ukranian",
                     Japan:"Japanese",
                     Norway:"Norwegian"}[x]
                     for x in (water, zebra)
                    ]
                    #list of conditions listed in logic checking format
                    for (red, green, ivory, yellow, blue) in orderings
                    if right(green, ivory)
                    
                    for (England, Spain, Ukraine, Japan, Norway) in orderings
                    if England is red
                    if Norway is first
                    if adjacent(Norway, blue)
                    
                    for (coffee, tea, milk, oj, water) in orderings
                    if coffee is green
                    if Ukraine is tea
                    if milk is third
                    
                    for (OGold, Kools, Chesters, LS, Parliaments) in orderings
                    if Kools is yellow
                    if LS is oj
                    if Japan is Parliaments
                    
                    for (dog, snails, fox, horse, zebra) in orderings
                    if Spain is dog
                    if OGold is snails
                    if adjacent(Chesters, fox)
                    if adjacent(Kools, horse))
    
    return ("It is the {} who drinks the water.\n"
            "The {} keeps the zebra.").format(result[0], result[1])
    
#credit to sjakobi for solution inspiration
