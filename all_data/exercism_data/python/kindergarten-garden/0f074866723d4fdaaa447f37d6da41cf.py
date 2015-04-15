__author__ = 'jeffmarkey'

class Garden:

    def __init__(self, content, students=''):

        self.__garden__ = content
        self.__collection__ = []

        # Split the content into rows...
        row1 = content.split('\n')[0]
        row2 = content.split('\n')[1]

        # Separate into chunks of size 2...
        for i in xrange(0, len(row1), 2):
            self.__collection__.append([row1[i:i+2],row2[i:i+2]])

        # Record the plants
        self.__plant_dictionary__ = {}
        self.__plant_dictionary__['R'] = 'Radishes'
        self.__plant_dictionary__['C'] = 'Clover'
        self.__plant_dictionary__['G'] = 'Grass'
        self.__plant_dictionary__['V'] = 'Violets'

        self.__children_order__ = {}

        # Record the children
        if(students == ''):
            self.__children_order__['Alice'] = 0
            self.__children_order__['Bob'] = 1
            self.__children_order__['Charlie'] = 2
            self.__children_order__['David'] = 3
            self.__children_order__['Eve'] = 4
            self.__children_order__['Fred'] = 5
            self.__children_order__['Ginny'] = 6
            self.__children_order__['Harriet'] = 7
            self.__children_order__['Ileana'] = 8
            self.__children_order__['Joseph'] = 9
            self.__children_order__['Kincaid'] = 10
            self.__children_order__['Larry'] = 11
        else:
            temporary_list = []
            for line in students:
                temporary_list.append(line)
            temporary_list.sort()
            for counter in range(len(temporary_list)):
                self.__children_order__[temporary_list[counter]] = counter

    def plants(self, name):
        value = self.__collection__[self.__children_order__[name]]
        return_list = []
        for line in value:
            for elm in line:
                return_list.append(self.__plant_dictionary__[elm])

        return return_list
