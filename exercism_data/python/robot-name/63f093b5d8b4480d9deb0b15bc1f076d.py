class RobotFactory:
    def __init__(self, name = ''):
        self.names = []
        self.init(name)

    def init(self,name):
        return None

class Robot(RobotFactory):

    def init(self, name = ''):
        
        if not name:
            
            from random import choice
            import string
            
            for i in range(2):
                name += choice(string.uppercase[:26])
            for i in range(3):
                name += choice(string.digits)
            
        if not name in self.names:
            self.names.append(name)
            self.name = name
        else:
            raise NameError("A robot named " + name + " already exists.")

    def reset(self,):
        self.names.remove(self.name)
        self.__init__()
