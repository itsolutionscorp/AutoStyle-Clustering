def sieve(topRange):
	primes = []
	testing = range(2,topRange+1)
	for item in testing:
		primes.append(item)
		for i in range(item*2,topRange+1,item):
			try:
				testing.remove(i)
			except:
				pass

	return primes

def main():
	sieve(10)

if __name__ == '__main__':
    main()
