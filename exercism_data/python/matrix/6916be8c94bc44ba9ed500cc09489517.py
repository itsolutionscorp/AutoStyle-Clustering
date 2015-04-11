# Dirk Herrmann's solution version 1 for exercism exercise "Matrix"

# This file implements class Matrix:
#
# * Each object of type Matrix represents a matrix of numbers.
#
# * The data member "rows" holds a list of the rows of the matrix, each row
#   being a list of numbers.  For example, rows could contain
#   [[1,2][3,4][5,6]].
#
# * The data member "columns" holds a list of the colums of the matrix, which
#   is the transposed content of "rows".  For example, columns would contain
#   [[1,3,5][2,4,6]] for the example matrix from the description of "rows".
#
# * An object of type Matrix is constructed from a string, containing numbers
#   separated by spaces and newlines.  The newlines separate the rows, and
#   within the rows the numbers are separated by spaces.  For example,
#   constructing a matrix from the string "1 2\n3 4\n5 6" will result in a
#   matrix equal to the one given as example in the description of "rows" and
#   "columns".

def parseTokenAsMatrixElement(token):
   return int(token)

def parseLineAsMatrixRow(line):
   tokens = line.split()
   return [parseTokenAsMatrixElement(token) for token in tokens]

def parseStringAsMatrixRows(string):
   lines = string.splitlines()
   return [parseLineAsMatrixRow(line) for line in lines]

def transposeRows(matrixAsLists):
   transposedTuples = zip(*matrixAsLists)
   return map(list, transposedTuples)

class Matrix:
   def __init__(self, string):
      self.rows = parseStringAsMatrixRows(string)
      self.columns = transposeRows(self.rows)
