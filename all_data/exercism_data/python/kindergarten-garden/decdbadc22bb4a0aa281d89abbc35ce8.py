
class Garden:

    def __init__(self, seeds, **kwargs):
        self.result = {}
        self._default_plants = {
            'G': 'Grass',
            'C': 'Clover',
            'R': 'Radishes',
            'V': 'Violets'
        }

        self._default_students = [
            'Alice', 'Bob', 'Charlie', 'David',
            'Eve', 'Fred', 'Ginny', 'Harriet',
            'Ileana', 'Joseph', 'Kincaid', 'Larry',
        ]

        self._plants = kwargs.get('plants', self._default_plants)
        self._students = sorted(kwargs.get('students', self._default_students))
        self._seeds = seeds.split(sep='\n')

        for i, v in enumerate(self._students):
            self.result[v] = [i+i, i+i+1]

    def plants(self, who):
        out = []
        for i in range(len(self._seeds)):
            for j in self.result[who]:
                out.append(self._plants[self._seeds[i][j]])
        return out
