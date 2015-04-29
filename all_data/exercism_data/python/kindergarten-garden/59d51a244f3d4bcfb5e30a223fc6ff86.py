children = ['Alice', 'Bob', 'Charlie', 'David', 'Eve', 'Fred', 'Ginny', 'Harriet', 'Ileana', 'Joseph', 'Kincaid', 'Larry']

class Garden(object):
    def __init__(self, garden):
        self.rows = garden.split()

    def plants(self, child):
        offset = children.index(child)
        self.answer = []
        for r in self.rows:
            self.answer.append(r[offset:offset+2])
        print self.answer
