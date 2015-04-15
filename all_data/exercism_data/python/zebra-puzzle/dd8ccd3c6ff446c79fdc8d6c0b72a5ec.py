def solution():
    return 'It is the '+get_beverage('water')+' who drinks the water.\n'\
                          'The '+get_pet('zebra')+' keeps the zebra.'

def get_beverage(beverage_name):
    drink_map = {'tea' : 'Ukrainian',
                 'water' : 'Norwegian',
                 'milk' : 'Englishman',
                 'orange juice' : 'Spaniard',
                 'coffee' : 'Japanese'}
    return drink_map[beverage_name]

def get_pet(pet_name):
    pet_map = {'zebra' : 'Japanese',
                 'dog' : 'Spaniard',
                 'snails' : 'Englishman',
                 'fox' : 'Norwegian',
                 'horse' : 'Ukrainian'}

    return pet_map[pet_name]
