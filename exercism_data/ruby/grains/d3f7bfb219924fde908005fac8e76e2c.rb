class Grains

	def square ( sq_num )
		2**(sq_num-1)
	end
	
	def total
		sum=0
		(0..63).each do |n|
			sum += 2**n
		end
		sum
	end

end
