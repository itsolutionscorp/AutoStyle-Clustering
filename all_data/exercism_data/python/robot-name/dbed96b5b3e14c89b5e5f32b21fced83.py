from string import ascii_uppercase

class Robot:
    count = 0

    def __init__(self):
        self._generate_name()
        
    def _generate_name(self):
        self.name = '{}{}{:03d}'.format(ascii_uppercase[Robot.count//10000],
                                        ascii_uppercase[Robot.count//1000%10],
                                        Robot.count%1000)
        Robot.count += 1

    def reset(self):
        self._generate_name()
