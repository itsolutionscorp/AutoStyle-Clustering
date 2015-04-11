class Allergies(object):

    # Allergens and their scores
    ALLERGENS = (
        ('eggs', 1),
        ('peanuts', 2),
        ('shellfish', 4),
        ('strawberries', 8),
        ('tomatoes', 16),
        ('chocolate', 32),
        ('pollen', 64),
        ('cats', 128),
    )

    # A mapping of item name: score
    ALLERGEN_SCORES = dict(ALLERGENS)

    def __init__(self, score):
        self._score = score

    def is_allergic_to(self, allergen):
        """Return True if the person is allergic to the given allergen"""
        return self._score & self.ALLERGEN_SCORES[allergen]

    @property
    def list(self):
        """List of all allergies for this person"""
        return [allergen for allergen, score in self.ALLERGENS
                if self._score & score]
