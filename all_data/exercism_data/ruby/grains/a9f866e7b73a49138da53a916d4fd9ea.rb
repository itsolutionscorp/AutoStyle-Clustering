class Grains
	def square num
		#the number of grains 
		#on the n-th square
		#equals to 2**(n-1)
		#because on the 1st square
		#we have 2**(1-1)=2**0=1
		2**(num-1)
	end
	def total
		#Using the formula for the sum
		#of geometric progression
		#starting with the second square we get
		#2*(2**(64-1) - 1)/(2-1)=
		#2*(2**63 - 1)=
		#2**64 - 2
		#Now, adding the grains on the first
		#square we get:
		#2**64 - 2 + 1=
		2**64 - 1
	end
end
