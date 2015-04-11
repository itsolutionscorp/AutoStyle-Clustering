from collections import defaultdict
from typing import Dict, Iterable, Iterator, Set, Tuple


class School:
    def __init__(self, name: str) -> None:
        self.db = defaultdict(Set[str])  # type: Dict[int, Set[str]]

    def add(self, name: str, grade: int) -> None:
        self.db[grade].add(name)

    def grade(self, grade: int) -> Set[str]:
        return self.db.get(grade, set())

    def sort(self) -> Iterator[Tuple[int, Iterable[str]]]:
        for grade in sorted(self.db):
            yield (grade, tuple(sorted(self.db[grade])))
