from itertools import islice, chain

class Garden(object):
    def __init__(self, garden, students = None):
        if not students:
            students = ['Alice', 'Bob', 'Charlie', 'David', 'Eve', 'Fred', 'Ginny', 'Harriet', 'Ileana', 'Joseph', 'Kincaid', 'Larry']

        self.__plants = {'C': 'Clover', 'G': 'Grass', 'R': 'Radishes', 'V': 'Violets'}
        self.__students = sorted(students)
        self.garden = garden.split()

    def plants(self, student):
        idx = self.__students.index(student)
        lst = list(self.flatten([next(islice(self.chunks(row, 2), idx, idx + 1)) for row in self.garden]))
        return [self.__plants[plant] for plant in lst]

    def chunks(self, iterable, chunksize):
        i = iter(iterable)

        while True:
            tmp = [list(islice(i, chunksize))]

            if not tmp[0]:
                break

            yield tmp.pop()

    def flatten(self, lst):
        return chain.from_iterable(lst)
