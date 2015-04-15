def solution():
    # There are five houses.
    houses = [list('') for i in range(5)]
    # Milk is drunk in the middle house
    houses[2] = ['Milk']
    # The Norwegian lives in the first house
    houses[0] = ['Norwegian']
    # The Norwegian lives next to the blue house
    for cnt,i in enumerate(houses):
        if 'Norwegian' in i:
            houses[cnt+1].append('blue')
    # Coffee is drunk in the green house
    # The green house can only be the fourth one
    houses[3] = ['green', 'coffee']
    # The green house is immediately to the right of the ivory house
    for cnt,i in enumerate(houses):
        if 'green' in i:
            houses[cnt+1].append('ivory')

    # The Englishman lives in the red house 
    # The Englishman can only live the in the house where Milk is drunk
    for cnt,i in enumerate(houses):
        if 'Milk' in i:
            houses[cnt].extend(['Englishman', 'red'])
    # Kools are smoked in the yellow house
    # The only house without a color till now is the first
    houses[0].extend(['Kools', 'yellow']) 
    # Kools are smoked in the house next to the house where the horse is kept
    for cnt,i in enumerate(houses):
        if 'Kools' in i:
            houses[cnt+1].append('horse')
    # The Lucky Strike smoker drinks orange juice
    # It can only be on a house that has only one attribute till now
    for cnt,i in enumerate(houses):
        if len(i)==1:
            houses[cnt].extend(['Lucky Strike', 'orange juice'])
    # The Ukranian drinks tea
    # It can only be the house that lacks Nationality and beverage ergo the
    # second
    houses[1].extend(['Ukranian', 'tea'])
    # The japanese smokes Parliaments
    houses[3].extend(['Japanese', 'Parliaments'])  

    # The spaniard owns the dog
    houses[4].extend(['Spaniard', 'dog'])
 
    # The Old Gold smoker owns snails
    houses[2].extend(['Old Gold', 'snails'])
    #The man who smokes Chesterfields lives in the house next to the man with
    # the fox
    houses[1].append('Chesterfields')
    houses[0].append('fox')

    #Norwegian is left without a beverage and the Japanese without a pet
    return """It is the Norwegian who drinks the water.
The Japanese keeps the zebra."""
 
solution()
