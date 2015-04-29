"""Allergies"""


class Allergies(object):
    """Allergy Score"""

    ITEM_TO_BITFLAG = (
        ("eggs", 1), ("peanuts", 2), ("shellfish", 4), ("strawberries", 8),
        ("tomatoes", 16), ("chocolate", 32), ("pollen", 64), ("cats", 128)
    )

    def __init__(self, score):
        """Create an allergy score."""
        self.list = []
        for item, bitflag in Allergies.ITEM_TO_BITFLAG:
            if score & bitflag:
                self.list.append(item)

    def is_allergic_to(self, item):
        """Return true if the score indicates an allergy to a given item."""
        return item in self.list
