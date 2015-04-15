class Garden:
    _DEFAULT_PLANTS = {'G': 'Grass',
                       'C': 'Clover',
                       'R': 'Radishes',
                       'V': 'Violets'}
    _DEFAULT_STUDENTS = ('Alice', 'Bob', 'Charlie', 'David', 'Eve', 'Fred', 'Ginny',
                         'Harriet','Ileana', 'Joseph', 'Kincaid', 'Larry')
    def __init__(self, cups: str, students=_DEFAULT_STUDENTS, plants=_DEFAULT_PLANTS):
        self._assignments = {student: [plants[p] for p in bunch]
                            for student, bunch in zip(sorted(students), Garden._bunches(cups))}

    @staticmethod
    def _bunches(s: str) -> 'Iterable[Tuple[str, str, str, str]]':
        second_row_start = len(s)//2+1
        return ((s[i], s[i+1], s[second_row_start+i], s[second_row_start+i+1])
                for i in range(0, len(s)//2, 2))

    def plants(self, name: str) -> 'List[str]':
        return self._assignments[name]
