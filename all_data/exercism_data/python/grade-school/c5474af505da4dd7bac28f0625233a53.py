from collections import defaultdict, OrderedDict
import copy

class School(object):

	def __init__(self, name="My School"):
		self._db = defaultdict(set)

	def add(self, name, grade):
		self._db[grade].add(name)

	def grade(self, grade):
		return self._db[grade]

	def sort(self):
		res = OrderedDict()
		for grade, names in sorted(self._db.iteritems()):
			res[grade] = tuple(sorted(names))
		return res

	@property
	def db(self):
		return copy.deepcopy(self._db)
