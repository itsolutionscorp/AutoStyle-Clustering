from random import randint

class Robot(object):
    """Class that represents an industrial robot"""

    def __init__(self, **filename):
        """Creates a new robot with a unique name, using a list of
        names from a given file or using a default file"""
        if filename:
            self._set_name(filename)
        else:
            self._set_name()

    def _set_name(self, existing_names='RobotNames.txt'):
        """Creates a name for the robot and checks a file
        to ensure the name is unique. If it is, the name is added
        to the file.
        """
        existing_name_list = []
        try:
            name_file = open(existing_names, 'r')
            for name in name_file:
                existing_name_list.append(name)
            name_file.close()
        except FileNotFoundError:
            pass
        
        while True:
            name = [chr(randint(ord('A'), ord('Z'))) for i in range(2)] + \
                    [str(randint(0,9)) for i in range(3)]
            name = ''.join(name)
            if name not in existing_name_list:
                break

        name_file = open('RobotNames.txt', 'a')
        name_file.write(name+'\n')
        name_file.close()

        self.name = name

    def reset(self, **filename):
        """Reinitalizes the robot, given a filename to avoid names from
        or relying on the default name file if no filename is given.
        """
        if filename:
            self.__init__(filename)
        else:
            self.__init__()
