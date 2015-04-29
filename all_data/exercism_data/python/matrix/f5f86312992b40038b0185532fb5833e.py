class Matrix:
    rows = []
    columns = []

    def __init__(self, matrixText):
        self.rows = []
        self.columns = []
        self.lines = []
        temp_lines = matrixText.split('\n')
        colIdx = 0
        firstRow = True
        firstLineSet = temp_lines[0].split(" ")
        for idx in range(0,len(firstLineSet)):
            firstLineSet[idx]=int(firstLineSet[idx])
        self.rows.append(firstLineSet)
        for idx in range(0, len(firstLineSet)):
            self.rows[0][idx] = int(self.rows[0][idx])
        for ch in self.rows[0]:
            self.columns.append([ch])
        colIdx = 1
        for idx in range(1, len(temp_lines)):
            parsedLine = temp_lines[idx].strip().split(" ")
            for idx in range(0,len(parsedLine)):
                parsedLine[idx]=int(parsedLine[idx])
            self.rows.append(parsedLine)
            for idx in range(0, len(parsedLine)):
                self.columns[idx].append(self.rows[-1][idx])
