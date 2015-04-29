class School():
    def __init__(self, name):
        self.database = {}
        self.name = name
        
    def db(self):
        return self.database

    def add(self, student, grade):
        checkempty = self.database.get(grade, set())
        if checkempty == set():
            checkempty.add(student)
            self.database[grade] = checkempty
        else:
            self.database[grade].add(student)

    def grade(self, grade):
        return self.database.get(grade, set())

    def sort(self):
        sup = '{'
        d = {}
        for x in sorted(self.database, key=self.database.get):
            sup+= str(x) + ': ('
            for y in sorted(self.database[x]):
                sup+= '"' + y + '",'
                print(x,y)
            sup+= '),'
            #add tuple
        sup = sup[:-1]+'}'
        print(sup)
        z = dict(sup)
        return z

"""
## For bonus points

Did you get the tests passing and the code clean? If you want to, these
are some additional things you could try:

- If your implementation allows outside code to mutate the school's
  internal DB directly, see if you can prevent this. Feel free to
  introduce additional tests.

Then please share your thoughts in a comment on the submission. Did this
experiment make the code better? Worse? Did you learn anything from it?
"""
