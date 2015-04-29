import pickle
from string import ascii_letters, maketrans, digits

'''
This is just the atbash_cipher from a previous exercise
modified to allow numbers, uppercase, and lowercase switching.
'''


def encode(text):
    alphabet = ascii_letters + digits
    code = text.translate(maketrans(alphabet, alphabet[::-1])).replace(" ", "")
    return " ".join(code[i:i + 5] for i in xrange(0, len(code), 5))


def decode(text):
    return encode(text).replace(" ", "")


class School(object):

    def __init__(self, name):
        self.name = name
        self.encodeddb = encode(pickle.dumps({}))
        self.db = pickle.loads(decode(self.encodeddb))

    def add(self, student, grade):
        database = self.db
        if grade in database.keys():
            database[grade].add(student)
        else:
            database[grade] = set([student])
        self.encodeddb = encode(pickle.dumps(database))

    def grade(self, grade):
        if grade in self.db:
            return self.db[grade]
        return set()

    def sort(self):
        sorteddb = {}
        for grade, students in self.db.iteritems():
            sortedstudents = sorted(students)
            sorteddb[grade] = tuple(sortedstudents)
        return sorteddb
