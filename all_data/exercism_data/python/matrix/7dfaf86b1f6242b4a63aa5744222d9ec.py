import re
import string

class Matrix:
      def __init__(self,inString):
          # initalize columns and rows
          self.rows = []
          self.columns = []
          # handle space after \n
          inString = re.sub('\n ','\n',inString)
          # split on newline
          splitLines = inString.split('\n')
          # create a row for each line
          for line in splitLines:
              row = [int(x) for x in line.split(' ')]
              self.rows.append(row)
          # get the number of columns & rows
          self.nCols = len(row)
          self.nRows = len(self.rows)
          # copy to columns
          for x in range(self.nCols):
              # new list for each column's data
              column = []
              for y in range(self.nRows):
                  column.append(self.rows[y][x])
              # append each column to columns
              self.columns.append(column)
      def size(self):
          return list([self.nRows,self.nCols])
