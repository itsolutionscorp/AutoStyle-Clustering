def __init__(self,input1,input2):
	self.distance = distance(input1,input2)
def distance(input1,input2):
	return len(input1) - sum(input1[i] == input2[i] for i in range(len(input1)))
