class Allergies:
    def __init__(self, value):
        self.list = []
        self.value = value
        self.items = {
            1: 'eggs',
            2: 'peanuts',
            4: 'shellfish',
            8: 'strawberries',
            16: 'tomatoes',
            32: 'chocolate',
            64: 'pollen',
            128: 'cats'
        }
        self.create_list(self.value)

    def is_allergic_to(self, name):
        value = self.value
        while value > 0:
            if self.items.get(value) == name:
                return True
            value -= 1
        return False

    def create_list(self, val):
        value = val
        leftover = 0
        while value > 0:
            item = self.items.get(value)
            if item:
                self.list.append(item)
                value = 0
            else:
                leftover += 1
                value -= 1

        if leftover > 0:
            self.create_list(leftover)
