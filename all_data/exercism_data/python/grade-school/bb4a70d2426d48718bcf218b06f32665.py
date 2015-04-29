class School:

    def __init__(self, name):
        self.name = name
        self.db = {}

    def add(self, student, grade):
        """Add student to grade."""
        self.db.setdefault(grade, set()).add(student)

    def grade(self, grade):
        """Return students for grade."""
        return self.db.get(grade, set())

    def sort(self):
        """Return sorted students grouped by grade."""
        return sorted(
            (grade, tuple(sorted(students)))
            for grade, students in self.db.items()
        )
