from collections import defaultdict
from threading import RLock

class School:
	def __init__(self, name):
		self.name = name
		self.db= defaultdict(set)
		self.lock = RLock()

	def add(self, student, gradeLvl):
		self.lock.acquire()
		with self.lock:
			self.db[gradeLvl].add(student)
			self.lock.release() 

	def grade(self, gradeLvl):
		return self.db[gradeLvl]

	def sort(self):
		self.lock.acquire()
		with self.lock:
			sorted(self.db)	
			return { k:tuple(v) for k,v in self.db.items() }
			self.lock.release()
