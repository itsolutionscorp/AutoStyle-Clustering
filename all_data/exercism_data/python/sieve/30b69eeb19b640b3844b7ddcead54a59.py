# uses the sieve of Eratosthenes to find all prime numbers between 2 and n (inclusive)
def sieve(n):
	answer=list(range(2,n+1))
	counter=0
	while counter<len(answer):
		grain=answer[counter]
		if n>=grain**2:
			for i in range(grain,int(n/grain)+1):
				if grain*i in answer:
					answer.remove(grain*i)
		counter+=1
	return answer
