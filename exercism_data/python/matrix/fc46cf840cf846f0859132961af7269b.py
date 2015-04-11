#!/usr/bin/python

class Matrix:
    def __init__(self, textmatrix):
        self.rows = Matrix.text_to_matrix(textmatrix)
        self.columns = Matrix.rows_to_columns(self.rows)
    
    @staticmethod
    def text_to_matrix(textmatrix):
        rows = textmatrix.split('\n')
        rows = [[int(datum) for datum in row.split()] for row in rows]
        return rows
    
    @staticmethod
    def rows_to_columns(rows):
        columns = [[row[i] for row in rows] for i in range(len(rows[0]))]
        return columns
