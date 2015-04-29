class Allergies(object):
    KNOWN_ALLERGIES = [
        ("eggs", 1),
        ("peanuts", 2),
        ("shellfish", 4),
        ("strawberries", 8),
        ("tomatoes", 16),
        ("chocolate", 32),
        ("pollen", 64),
        ("cats", 128)
    ]
    # honestly, this is kinda dumb. the tester should be using a set or at
    # least a sorted list. there's no reason to force the output of self.list()
    # to be ordered and unsorted.

    def __init__(self, bitmask):
        self._list = [k for k,v in self.KNOWN_ALLERGIES if bitmask & v]

    def is_allergic_to(self, allergy_name):
        return allergy_name in self._list

    @property
    def list(self):
        return self._list
