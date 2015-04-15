import itertools

def solution():
	for row in _all_solutions().__next__():
		person, house, animal, drink, smoke = row
		if animal == 'zebra':
			zebra_owner = person
		if drink == 'water':
			water_drinker = person
	return "It is the {} who drinks the water.\nThe {} keeps the zebra.".format(water_drinker, zebra_owner)

def _all_solutions():
	for people_order in itertools.permutations(_people):
		# 10. The Norwegian lives in the first house.
		if people_order[0] != 'Norwegian':
			continue
		for house_order in itertools.permutations(_houses):
			# 15. The Norwegian lives next to the blue house.
			if house_order[1] != 'blue':
				continue
			# 6. The green house is immediately to the right of the ivory house.
			if len("".join(house_order).split('ivorygreen')) != 2:
				continue
			# 2. The Englishman lives in the red house.
			if ('Englishman', 'red') not in set(zip(people_order, house_order)):
				continue
			for drink_order in itertools.permutations(_drinks):
				# 9. Milk is drunk in the middle house.
				if drink_order[2] != 'milk':
					continue
				# 4. Coffee is drunk in the green house.
				if ('coffee', 'green') not in set(zip(drink_order, house_order)):
					continue
				# 5. The Ukrainian drinks tea.
				if ('Ukrainian', 'tea') not in set(zip(people_order, drink_order)):
					continue
				for smoke_order in itertools.permutations(_smokes):
					# 8. Kools are smoked in the yellow house.
					if ('Kools', 'yellow') not in set(zip(smoke_order, house_order)):
						continue
					# 13. The Lucky Strike smoker drinks orange juice.
					if ('Lucky Strike', 'orange juice') not in set(zip(smoke_order, drink_order)):
						continue
					# 14. The Japanese smokes Parliaments.
					if ('Japanese', 'Parliaments') not in set(zip(people_order, smoke_order)):
						continue
					for animal_order in itertools.permutations(_animals):
						# 7. The  smoker owns snails.
						if ('Old Gold', 'snails') not in set(zip(smoke_order, animal_order)):
							continue
						# 3. The Spaniard owns the dog.
						if ('Spaniard', 'dog') not in set(zip(people_order, animal_order)):
							continue
						# 11. The man who smokes Chesterfields lives in the house next to the man with the fox.
						if abs(smoke_order.index('Chesterfields') - animal_order.index('fox')) != 1:
							continue
						# 12. Kools are smoked in the house next to the house where the horse is kept.
						if abs(smoke_order.index('Kools') - animal_order.index('horse')) != 1:
							continue
						yield list(zip(people_order, house_order, animal_order, drink_order, smoke_order))

_people = 'Englishman  Spaniard  Ukrainian  Norwegian  Japanese'.split('  ')
_houses = 'red  green  ivory  yellow  blue'.split('  ')
_animals = 'dog  snails  fox  horse  zebra'.split('  ')
_drinks = 'coffee  tea  milk  water  orange juice'.split('  ')
_smokes = 'Chesterfields  Lucky Strike  Old Gold  Kools  Parliaments'.split('  ')
