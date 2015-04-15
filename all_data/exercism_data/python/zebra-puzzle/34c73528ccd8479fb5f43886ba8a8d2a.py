def undefined_house():
	return {'color': {'red', 'green', 'ivory', 'yellow', 'blue'}, 
			'nation': {'Englishman', 'Spaniard', 'Ukrainian', 'Norwegian', 'Japanese'}, 
			'pet': {'dog', 'fox', 'snails', 'horse', 'zebra'}, 
			'beverage': {'coffee', 'tea', 'milk', 'juice', 'water'}, 
			'cigaret': {'Old Gold', 'Kools', 'Chesterfields', 'Lucky Strike', 'Parliaments'}}

def copy_house(house):
	return {key: set(v) for key, v in house.items()}

def copy_config(houses):
	return [copy_house(house) for house in houses]

def remove(category, value):
	def remover(house):
		house = copy_house(house)
		house[category] -= {value}
		return house
	return remover

def has_not(category, value):
	def checker(houses, index):
		return houses[index] is None or value not in houses[index][category]
	return checker

def next_to(test):
	def checker(houses, index):
		if index > 0 and not test(houses, index - 1):
			return False
		if index + 1 < len(houses) and not test(houses, index + 1):
			return False
		return True
	return checker

def modify(houses_test, test, houses_action, action):
	h = []
	for ht_idx, ha in zip(range(len(houses_test)), houses_action):
		if test(houses_test, ht_idx):
			h.append(action(ha))
		else:
			h.append(ha)
	return h

def remove_duplicates(houses):
	houses = copy_config(houses)

	#if there is only one possibility for an attribute in
	#one house, it can not be chosen in any other house
	for house_index, house in enumerate(houses):
		for attr in house:
			if len(house[attr]) == 1:
				for other_house_index, other_house in enumerate(houses):
					if other_house_index == house_index: continue
					other_house[attr] -= house[attr]

	#if there is only one house that can choose a specific value,
	#that house has to take that value
	for house in houses:
		for attr in house:
			for value in house[attr]:
				if sum(1 for other_house in houses if value in other_house[attr]) == 1:
					house[attr] = {value}
					break

	return houses


def right(houses):
	return houses[1:] + [None]

def left(houses):
	return [None] + houses[:-1]

def ambigous(houses):
	return any(any(len(v) >= 2 for v in house.values()) for house in houses)

def valid(houses):
	return all(all(len(v) >= 1 for v in house.values()) for house in houses)

def extract_solution_text(houses):
	water_house = [house for house in houses if house['beverage'] == {'water'}][0]
	zebra_house = [house for house in houses if house['pet'] == {'zebra'}][0]

	return ("It is the {0} who drinks the water.\n"
            "The {1} keeps the zebra.").format(next(iter(water_house['nation'])),
            								   next(iter(zebra_house['nation'])))

def solution():
	#1. There are five houses.
	houses = [undefined_house() for i in range(5)]
	
	stack = [houses]
	while stack:
		top = reduce_by_rules(stack.pop())

		if not valid(top): continue
		if not ambigous(top): return extract_solution_text(top)

		for house_index, house in enumerate(top):
			for attributes in house:
				if len(top[house_index][attributes]) > 1: #that attribute is ambigous
					for value in top[house_index][attributes]: #pick one value
						branch = copy_config(top) #and try to solve
						branch[house_index][attributes] = {value} #with that value
						stack.append(branch) #chosen

	return None

def reduce_by_rules(houses):
	houses = copy_config(houses)

	#9. Milk is drunk in the middle house.
	houses[2]['beverage'] = {'milk'}

	#10. The Norwegian lives in the first house.
	houses[0]['nation'] = {'Norwegian'}

	for i in range(30):
		houses = remove_duplicates(houses)

		#2. The Englishman lives in the red house.
		houses = modify(houses, has_not('nation', 'Englishman'), houses, remove('color', 'red'))
		houses = modify(houses, has_not('color', 'red'), houses, remove('nation', 'Englishman'))

		#3. The Spaniard owns the dog.
		houses = modify(houses, has_not('nation', 'Spaniard'), houses, remove('pet', 'dog'))
		houses = modify(houses, has_not('pet', 'dog'), houses, remove('nation', 'Spaniard'))

		#4. Coffee is drunk in the green house.
		houses = modify(houses, has_not('color', 'green'), houses, remove('beverage', 'coffee'))
		houses = modify(houses, has_not('beverage', 'coffee'), houses, remove('color', 'green'))

		#5. The Ukrainian drinks tea.
		houses = modify(houses, has_not('nation', 'Ukrainian'), houses, remove('beverage', 'tea'))
		houses = modify(houses, has_not('beverage', 'tea'), houses, remove('nation', 'Ukrainian'))

		#6. The green house is immediately to the right of the ivory house.
		houses = modify(left(houses), has_not('color', 'ivory'), houses, remove('color', 'green'))
		houses = modify(right(houses), has_not('color', 'green'), houses, remove('color', 'ivory'))

		#7. The Old Gold smoker owns snails.
		houses = modify(houses, has_not('cigaret', 'Old Gold'), houses, remove('pet', 'snails'))
		houses = modify(houses, has_not('pet', 'snails'), houses, remove('cigaret', 'Old Gold'))

		#8. Kools are smoked in the yellow house.
		houses = modify(houses, has_not('cigaret', 'Kools'), houses, remove('color', 'yellow'))
		houses = modify(houses, has_not('color', 'yellow'), houses, remove('cigaret', 'Kools'))

		#11. The man who smokes Chesterfields lives in the house next to the man with the fox.
		houses = modify(houses, next_to(has_not('cigaret', 'Chesterfields')), houses, remove('pet', 'fox'))
		houses = modify(houses, next_to(has_not('pet', 'fox')), houses, remove('cigaret', 'Chesterfields'))
		
		#12. Kools are smoked in the house next to the house where the horse is kept.
		houses = modify(houses, next_to(has_not('cigaret', 'Kools')), houses, remove('pet', 'horse'))
		houses = modify(houses, next_to(has_not('pet', 'horse')), houses, remove('cigaret', 'Kools'))

		#13. The Lucky Strike smoker drinks orange juice.
		houses = modify(houses, has_not('cigaret', 'Lucky Strike'), houses, remove('beverage', 'juice'))
		houses = modify(houses, has_not('beverage', 'juice'), houses, remove('cigaret', 'Lucky Strike'))
		
		#14. The Japanese smokes Parliaments.
		houses = modify(houses, has_not('nation', 'Japanese'), houses, remove('cigaret', 'Parliaments'))
		houses = modify(houses, has_not('cigaret', 'Parliaments'), houses, remove('nation', 'Japanese'))

		#15. The Norwegian lives next to the blue house.
		houses = modify(houses, next_to(has_not('nation', 'Norwegian')), houses, remove('color', 'blue'))
		houses = modify(houses, next_to(has_not('color', 'blue')), houses, remove('nation', 'Norwegian'))
	return houses
