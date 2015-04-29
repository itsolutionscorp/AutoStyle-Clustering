from collections import defaultdict, deque
from typing import Dict, Iterable, List


PLANTS = {
    'R': 'Radishes',
    'C': 'Clover',
    'G': 'Grass',
    'V': 'Violets',
}
STUDENTS = (
    'Alice', 'Bob', 'Charlie', 'David',
    'Eve', 'Fred', 'Ginny', 'Harriet',
    'Ileana', 'Joseph', 'Kincaid', 'Larry'
)


class Garden:
    def __init__(self, garden: str, students: Iterable[str]=STUDENTS, grouping: int=2) -> None:
        self.students = defaultdict(List[str])  # type: Dict[str, List[str]]

        if isinstance(students, list):
            students.sort()

        for group in garden.split():
            group_queue = deque(group)
            for student in students:
                for x in range(grouping):
                    self.students[student].append(PLANTS[group_queue.popleft()])
                if not group_queue:
                    break

    def plants(self, student: str) -> List[str]:
        return self.students.get(student)
