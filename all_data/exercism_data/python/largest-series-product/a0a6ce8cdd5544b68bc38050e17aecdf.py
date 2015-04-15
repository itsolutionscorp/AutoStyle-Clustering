from numpy import prod


class Series(object):

    def __init__(self, my_str):
        try:
            self.__lst = [int(char) for char in my_str]
        except:
            raise ValueError("Please input a string containing only digits")
        self.__length = len(my_str)

    def __validate_input(self, n):
        if n > self.__length:
            raise ValueError("Invalid slice length for this series: {0}"
                             .format(n))

    def slices(self, n):
        self.__validate_input(n)
        return [self.__lst[i:i+n] for i in range(0, self.__length - n + 1)]

    def largest_product(self, n):
        self.__validate_input(n)
        return max(prod(x) for x in self.slices(n))
