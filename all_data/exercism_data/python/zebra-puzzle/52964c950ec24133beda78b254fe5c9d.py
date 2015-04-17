from itertools import product, combinations
from copy import copy
from random import choice

# none of these tuples except the first are in any particular order
_categories={"number":(0,1,2,3,4), # refers to house positions, arranged left to right
	"color":("red", "green", "ivory", "yellow", "blue"),
	"national extraction":("Englishman", "Spaniard", "Ukranian", "Norwegian", "Japanese"),
	"pet":("dog", "fox", "horse", "zebra", "snails"),
	"beverage":("coffee", "milk", "orange juice", "water", "tea"),
	"cigarettes":("Old Gold", "Kools", "Chesterfields", "Lucky Strike", "Parliaments")
	}

class LogicGridContradiction(Exception):
	def __init__(self,message=None):
		super(LogicGridContradiction,self).__init__(message)

# a particular state of a logic grid
# contradictions in information provided to LogicGrid may not be detected until a solution is attempted
class LogicGrid(object):
	REQUIRED=1
	FORBIDDEN=-1
	UNKNOWN=0

	# categories must either be an instance of LogicGrid (to be copied) or a dictionary whose keys are of type str and whose values are non-overlapping iterables of distinguishable objects
	def __init__(self,categories):
		if isinstance(categories,LogicGrid):
			self.categories=copy(categories.categories)
			self.functions=copy(categories.functions)
			self.grid=copy(categories.grid)
		else:
			self.categories=copy(categories)
			self.functions=set()
			self.grid={}
			for label_pair_iter in combinations(categories.values(),2):
				for label_pair in product(*label_pair_iter):
					self.grid[frozenset(label_pair)]=LogicGrid.UNKNOWN

	# forbids the specified pair of properties from occurring together
	# thing1 and thing2 must be elements of distinct values from self.categories unless thing2==None in which case thing1 must be a frozenset (pair) of elements of distinct values from self.categories
	def forbid(self,thing1,thing2=None):
		pair=thing1 if thing2==None else frozenset([thing1,thing2])
		if self.grid[pair]==LogicGrid.REQUIRED:
			raise LogicGridContradiction("%s and %s are already required."%(thing1,thing2))
		self.grid[pair]=LogicGrid.FORBIDDEN

	# requires the specified pair of properties to occur together
	# thing1 and thing2 must be elements of distinct values from self.categories unless thing2==None in which case thing1 must be a frozenset (pair) of elements of distinct values from self.categories
	def require(self,thing1,thing2=None):
		pair=thing1 if thing2==None else frozenset([thing1,thing2])
		if self.grid[pair]==LogicGrid.FORBIDDEN:
			raise LogicGridContradiction("%s and %s are already forbidden."%(thing1,thing2))
		self.grid[pair]=LogicGrid.REQUIRED

	# returns the current state of association between two properties
	# thing1 and thing2 must be elements of distinct values from self.categories unless thing2==None in which case thing1 must be a frozenset (pair) of elements of distinct values from self.categories
	def current_value(self,thing1,thing2=None):
		return self.grid[thing1 if thing2==None else frozenset([thing1,thing2])]

	# returns the name of the category to which element belongs, None otherwise
	def get_category_name(self,element):
		for name in self.categories.keys():
			if element in self.categories[name]:
				return name
		return None
		
	# returns the category to which element belongs, None otherwise
	def get_category(self,element):
		for cat in self.categories.values():
			if element in cat:
				return cat
		return None

	# returns the contents of the category to which element belongs other than element itself, None otherwise
	def others_of_same_type(self,element):
		for cat_list in self.categories.values():
			if element in cat_list:
				return [ thing for thing in cat_list if thing!=element ]
		return None

	# returns the thing which must be associated with element from within category. returns None if no such element (yet) exists
	# category refers to the actual category rather than to its name.
	def get_required(self,category,element):
		if element in category:
			return element
		for thing in category:
			if self.current_value(thing,element)==LogicGrid.REQUIRED:
				return thing
		return None

	# returns the thing which must be associated with element from within named category. returns None if no such element (yet) exists
	def get_required_by_name(self,category_name,element):
		return self.get_required(self.categories[category_name],element)

	# enforces the rule that no element from one category can be associated with more than one element from another category
	def _enforce_uniqueness(self):
		for pair,value in self.grid.items():
			if value==LogicGrid.REQUIRED:
				a,b=tuple(pair)
				for c in self.others_of_same_type(a):
					self.forbid(c,b)
				for c in self.others_of_same_type(b):
					self.forbid(a,c)

	# enforces the rule that every element from one category must be associated with some element from each other category
	def _enforce_existence(self):
		for pair in self.grid.keys():
			a,b=tuple(pair)
			last_a=True
			for c in self.others_of_same_type(a):
				last_a=last_a and self.current_value(c,b)==LogicGrid.FORBIDDEN
			if last_a:
				self.require(a,b)
			else:
				last_b=True
				for c in self.others_of_same_type(b):
					last_b=last_b and self.current_value(a,c)==LogicGrid.FORBIDDEN
				if last_b:
					self.require(a,b)

	# enforces the rule that if A and B are associated then for every C, self.grid(A,C) must equal self.grid(B,C)
	def _enforce_transitivity(self):
		for pair,value in self.grid.items():
			if value==LogicGrid.REQUIRED:
				a,b=tuple(pair)
				c_values=[]
				for cat in self.categories.values():
					if a not in cat and b not in cat:
						c_values.extend(cat)
				for c in c_values:
					ac_val,bc_val=self.current_value(a,c),self.current_value(b,c)
					if bc_val==LogicGrid.REQUIRED:
						self.require(a,c)
					if bc_val==LogicGrid.FORBIDDEN:
						self.forbid(a,c)
					if ac_val==LogicGrid.REQUIRED:
						self.require(b,c)
					if ac_val==LogicGrid.FORBIDDEN:
						self.forbid(b,c)

	# creates a copy of this LogicGrid
	def __copy__(self):
		return LogicGrid(self)

	# determines if self and other are equal
	# since equality of functions is defined by reference, two functions with the same action will cause LogicGrids containing those functions to be evaluated as unequal
	# since iterables are not always compared as frozensets, if a category in self is defined by a type_a iterable and the corresponding category in other is defined by a type_b iterable then this function may evaluate incorrectly as False
	def __eq__(self,other):
		if isinstance(other,LogicGrid):
			return self.categories==other.categories and self.functions==other.functions and self.grid==other.grid
		return False

	# adds a function to the list of functions to be performed during a solving attempt. The function must accept one argument, of type LogicGrid
	# these functions represent information which cannot be entirely encapsulated in the grid format.
	def add_function(self,target):
		self.functions.add(target)

	# determines whether or not this LogicGrid is completed (defined as containing no UNKNOWNs - it is possible to reach this state with contradictory information)
	def is_complete(self):
		for value in self.grid.values():
			if value==LogicGrid.UNKNOWN:
				return False
		return True

	# attempts to completely solve this LogicGrid. returns True if the solution is complete at the end of running
	def attempt_solution(self):
		last_run=None
		while last_run!=self:
			last_run=copy(self)
			self._enforce_existence()
			self._enforce_uniqueness()
			self._enforce_transitivity()
			for f in self.functions:
				f(self)
		return self.is_complete()

	# makes a random positive guess about a single affiliation and returns that guess. returns None if no guesses remain to be made
	def guess(self):
		possible_guesses=[ pair for pair in self.grid.keys() if self.grid[pair]==LogicGrid.UNKNOWN ]
		if len(possible_guesses)==0:
			return None
		actual_guess=choice(possible_guesses)
		self.require(actual_guess)
		return actual_guess

# enforces condition 6
def _condition_6(grid):
	green=grid.get_required(_categories["number"],"green")
	ivory=grid.get_required(_categories["number"],"ivory")
	if green!=None:
		grid.require("ivory",green-1)
	if ivory!=None:
		grid.require("green",ivory+1)

# requires that thing1 and thing2 be in adjacent houses
def _adjacent_houses(grid,thing1,thing2):
	nums=_categories["number"]
	thing1_vals=[ grid.current_value(thing1,n) for n in nums ]
	thing2_vals=[ grid.current_value(thing2,n) for n in nums ]
	for i in nums:
		if (i-1 not in nums or thing1_vals[i-1]==LogicGrid.FORBIDDEN) and (i+1 not in nums or thing1_vals[i+1]==LogicGrid.FORBIDDEN):
			grid.forbid(thing2,i)
		if (i-1 not in nums or thing2_vals[i-1]==LogicGrid.FORBIDDEN) and (i+1 not in nums or thing2_vals[i+1]==LogicGrid.FORBIDDEN):
			grid.forbid(thing1,i)

# finds a random solution among those which are possible and returns it. does not alter the original grid.
def find_random_solution(logic_grid):
	grid=copy(logic_grid)
	if grid.attempt_solution():
		return grid
	else:
		grid2=copy(grid)
		pair=grid2.guess()
		try:
			return find_random_solution(grid2)
		except LogicGridContradiction:
			grid.forbid(pair)
			return find_random_solution(grid)

# finds a random partial solution which requires every condition in needs and returns it. does not alter the original grid.
# needs must be a dictionary whose keys are properties belonging to an element of logic_grid.categories.values() and whose values must be elements of logic_grid.categories.keys(). this condition will not be checked
def find_random_partial_solution(logic_grid,needs):
	grid=copy(logic_grid)
	grid.attempt_solution()
	if all([ grid.get_required_by_name(value,key)!=None for key,value in needs.items() ]):
		return grid
	else:
		grid2=copy(grid)
		pair=grid2.guess()
		try:
			return find_random_partial_solution(grid2,needs)
		except LogicGridContradiction:
			grid.forbid(pair)
			return find_random_partial_solution(grid,needs)

# solves the zebra puzzle
def solution():
	grid=LogicGrid(_categories)
	grid.require("Englishman","red")                                               # condition 2
	grid.require("Spaniard","dog")                                                 # condition 3
	grid.require("coffee","green")                                                 # condition 4
	grid.require("Ukranian","tea")                                                 # condition 5
	grid.forbid("green",0)
	grid.forbid("ivory",4)
	grid.add_function(_condition_6)                                                # condition 6
	grid.require("Old Gold","snails")                                              # condition 7
	grid.require("Kools","yellow")                                                 # condition 8
	grid.require("milk",2)                                                         # condition 9
	grid.require("Norwegian",0)                                                    # condition 10
	grid.forbid("Chesterfields","fox")
	grid.add_function(lambda x:_adjacent_houses(x,"Chesterfields","fox"))          # condition 11
	grid.forbid("Kools","horse")
	grid.add_function(lambda x:_adjacent_houses(x,"Kools","horse"))                # condition 12
	grid.require("Lucky Strike","orange juice")                                    # condition 13
	grid.require("Japanese","Parliaments")                                         # condition 14
	grid.forbid("Norwegian","blue")
	grid.add_function(lambda x:_adjacent_houses(x,"Norwegian","blue"))             # condition 15
	if grid.attempt_solution():
		answer=grid
	else:
		needs={"water":"national extraction", "zebra":"national extraction"}
		answer=find_random_partial_solution(grid,needs)
	return "It is the %s who drinks the water.\nThe %s keeps the zebra."%(answer.get_required_by_name("national extraction","water"),answer.get_required_by_name("national extraction","zebra"))