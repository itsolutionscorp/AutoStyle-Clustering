class Matrix(object):

    def __init__(self, s):
        """ Store a single list with all elements.
            Store the length of a row in i, and the
            length of a column in j

            Also will check if input items per row is correct.
        """
            
        self.__i = 0
        self.__j = 0
        self.__items = []
        self.rows = []
        self.columns = []
        
        for row in s.split("\n"):
            i = 0
            for column in row.split():
                i += 1
                self.__items.append(int(column))
            self.__j += 1

            if self.__i != 0:
                assert self.__i == i, "Row # " + str(self.__j) + ("(or #1)" if self.__j == 2 else '') + " has dissimilar amount of values"
            else:
                self.__i = i

        for i in range(self.__j):
            self.rows.append([self.__items[self.__i*i+j] for j in range(self.__i)])

        for j in range(self.__i):
            self.columns.append([self.__items[self.__i*i+j] for i in range(self.__j)])
