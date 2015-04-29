class Grains
	def square(n)
		2**(n-1)
	end
	def total 
		sum=0
		(1..64).each do |i|
			sum=sum+self.square(i)
		end
		sum
	end
	
end
