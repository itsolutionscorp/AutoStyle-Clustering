# matrix.py
"""
Matrix containing
- A list of the rows,
  reading each row left-to-right while moving top-to-bottom across the rows,
- A list of the columns,
  reading each column top-to-bottom while moving from left-to-right.
"""

class Matrix:
	def __init__(self,s):
		""" Build rows/columns as dictionaries """
		s		 	 = s.split("\n")
		self.rows    = dict(zip(range(len(s)),[[int(e) for e in r.split()] for i,r in enumerate(s)]))
		
		n		 	 = max(len(x) for x in self.rows.values())
		self.columns = dict(zip(range(n),[[r[j] for r in self.rows.values()] for j in range(n)]))
