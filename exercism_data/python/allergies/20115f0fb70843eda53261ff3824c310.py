allergies = {
    'eggs': 1,
    'peanuts': 2,
    'shellfish': 4,
    'strawberries': 8,
    'tomatoes': 16,
    'chocolate': 32,
    'pollen': 64,
    'cats': 128
}


def _is_odd(num):
    return num % 2 != 0


# the idea is to recursively divide number by 2,
# if odd, then add the next smallest element
def find_list(num):
    my_list = list()
    i = 0
    while True:
        if _is_odd(num):
            my_list.extend(k for k, v in allergies.iteritems() if v == 2**i)
            num -= 1
        i += 1
        num /= 2
        if num <= 0:
            break
    return my_list


class Allergies(object):

    def __init__(self, score):
        self.list = find_list(score)

    def is_allergic_to(self, allergen):
        return allergen in self.list
