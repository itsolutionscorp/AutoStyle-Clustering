from string import uppercase, digits
import unittest
import math
import random

class Robot:
	registry = set()

	def __init__(self):
		self.assign_name()

	def reset(self):
		Robot.registry.remove(self.name)
		self.assign_name()

	def assign_name(self):
		name = self.gen_name()
		while name in Robot.registry:
			name = self.gen_name()
		Robot.registry.add(name)
		self.name = name

	def gen_name(self):
		name = []
		for (count, symbols) in [(2, uppercase), (3, digits)]:
			name += [random.choice(symbols) for _ in range(count)]
		return ''.join(name)

class ExtraRobotTest(unittest.TestCase):

	def test_name_is_registered(self):
		robot = Robot()
		self.assertTrue(robot.name in Robot.registry)


	def test_name_is_recycled(self):
		robot = Robot()
		old_name = robot.name
		robot.reset()
		self.assertTrue(old_name not in Robot.registry)

	'''
	This code uses birthday paradox approximation to calculate number of 
	robots needed to generate a naming collision with specified number
	http://en.wikipedia.org/wiki/Birthday_problem#Cast_as_a_collision_problem
	'''
	def test_name_doesnt_collide(self):
		confidence = 0.999  # be 99.9% confident of generating collission
		n = int(math.sqrt(2*math.log(1/(1-confidence))*26**2*10**3))
		for i in range(n):
			_ = Robot()
		self.assertEquals(n, len(Robot.registry))

if __name__ == '__main__':
	unittest.main()
