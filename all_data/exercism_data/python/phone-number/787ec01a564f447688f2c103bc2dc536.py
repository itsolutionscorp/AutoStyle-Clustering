import re 

class Phone(object):
    def __init__(self, input):
        length = len(input)

        m = re.match( r'^1?\(?(\d\d\d)\)?[-.\s]*(\d\d\d)[-.\s]*(\d\d\d\d)$', input)

        if (m):
            self._number = [m.group(1), m.group(2), m.group(3)]
        else:
            self._number = ["000", "000", "0000"]

        self.number = self._build_no("{0}{1}{2}")

    def area_code(self):
        return self._number[0]

    def pretty(self):
        return self._build_no("({0}) {1}-{2}")

    def _build_no(self, template):
        return template.format(*self._number)
        
