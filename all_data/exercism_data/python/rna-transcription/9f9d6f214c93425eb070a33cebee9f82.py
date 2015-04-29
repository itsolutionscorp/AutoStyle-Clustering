class DNA:
    def __init__(self, userInput):
        self.userInput = userInput
    def to_rna(self):
        newArr = []
        for i in self.userInput:
            if (i == 'G'):
                newArr.append("C")
            if (i == 'C'):
                newArr.append("G")
            if (i == 'T'):
                newArr.append("A")
            if (i == 'A'):
                newArr.append("U")
        newArr = "".join(newArr)
        return newArr
