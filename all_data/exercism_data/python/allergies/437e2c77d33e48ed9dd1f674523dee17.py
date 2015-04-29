CODES = {
    1:'eggs',
    2:'peanuts',
    4:'shellfish',
    8:'strawberries',
    16:'tomatoes',
    32:'chocolate',
    64:'pollen',
    128:'cats'
}

class Allergies(object):
    def __init__(self, code):
        self.code = code
        self.list = [CODES[key] for key in sorted(CODES) if code & key == key]

    def is_allergic_to(self, item):
        return item in self.list
