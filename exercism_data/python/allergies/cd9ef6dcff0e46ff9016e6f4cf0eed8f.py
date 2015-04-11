class Allergies(object):

	def __init__(self,score):
		self.score=score

	def is_allergic_to(self,item):
		if item in self.lists:
			return True
		else:
			return False
		
	@classmethod
	def list(self):
		self.lists=[]
		if self.score==0:
			return 
		if self.score ==1:
			self.lists.append('eggs')
			return self.lists
