from itertools import combinations

def gcd(*args):
	if len(args)==0:
		return 1
	elif len(args)==1:
		return args[0]
	elif len(args)==2:
		a,b=args
		while b:
			a,b=b,a%b
		return a
	else:
		answer=args[0]
		for a in args[1:]:
			answer=gcd(answer,a)
		return answer
		
def lcm(*args):
	if len(args)==0:
		return 1
	elif len(args)==1:
		return args[0]
	elif len(args)==2:
		return args[0]*args[1]//gcd(*args)
	else:
		answer=args[0]
		for a in args[1:]:
			answer=lcm(answer,a)
		return answer

# constructs sums of multiples of given numbers up to but not including another given number
class SumOfMultiples(object):
	# args consists of the list/tuple of numbers whose multiples will be summed
	def __init__(self,*args):
		self.multiples_of=args if len(args)>0 else (3,5)

	# n is the int up to which we will sum multiples
	def to(self,n):
		return self.to1(n)

	# n is the int up to which we will sum multiples
	# works best with a small amount of small numbers
	def to1(self,n):
		answer=0
		for i in range(1,n):
			if any([ i%k==0 for k in self.multiples_of ]):
				answer+=i
		return answer

	# n is the int up to which we will sum multiples
	# works best with a large amount of numbers
	def to2(self,n):
		answer=0
		for ii in range(len(self.multiples_of)):
			i=self.multiples_of[ii]
			for j in range(i,n,i):
				if not any([ j%k==0 for k in self.multiples_of[:ii] ]):
					answer+=j
		return answer

	# n is the int up to which we will sum multiples
	# works best with a small amount of large numbers
	def to3(self,n):
		answer=0
		targets=set(self.multiples_of)
		for x in range(1,len(targets)+1):
			for ii in combinations(targets,x):
				i=lcm(*ii)
				answer+=((-1)**(x+1))*sum(range(i,n,i))
		return answer
