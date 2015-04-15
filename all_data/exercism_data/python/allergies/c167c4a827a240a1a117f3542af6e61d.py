from typing import List

ALLERGY_TESTS = (
    'eggs',
    'peanuts',
    'shellfish',
    'strawberries',
    'tomatoes',
    'chocolate',
    'pollen',
    'cats',
)


class Allergies:
    def __init__(self, score: int) -> None:
        self._cache = None  # type: List[str]
        self.score = score

    @property
    def list(self) -> List[str]:
        if not self._cache:
            self._cache = [
                test_name
                for idx, test_name in enumerate(ALLERGY_TESTS)
                if self.score & 1 << idx
            ]

        return self._cache

    def is_allergic_to(self, test_name: str) -> bool:
        return test_name in self.list
