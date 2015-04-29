'''Generate random names for a robot. After reset, generate a new name'''


import random

def _gen_name():
	alpha = list('ABCDEFGHIJKLMNOPQRSTUVWXYZ')
	return ''.join([random.choice(alpha) for ii in range(2)]) + \
			str(random.randint(0,1000)).zfill(3)

class Robot():

	def __init__(self):
		self.name = _gen_name()

	def reset(self):
		self.name = _gen_name()

if __name__ == '__main__':


       robot = Robot()
       name = robot.name
       print(name)
       robot.reset()
       name = robot.name
       print(name)
