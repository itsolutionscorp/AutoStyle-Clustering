module Hamming

	def self.compute(arg1, arg2)
		count = 0
		
		(0...shortest(arg1, arg2)).each { |i| count +=1 unless arg1[i] == arg2[i] }
		
		count
	end	

private 

	def self.shortest(a, b)
		a.size > b.size ? b.size : a.size
	end

end
