# 1st Iteration

# class Hamming
# 	def self.compute(a,b)
# 		least = [a,b].min.size
# 		counter = 0
# 		(0..least - 1).each do |i|
# 			if a[i] != b[i]
# 				counter += 1
# 			else
# 				next
# 			end
# 		end
# 		counter
# 	end	
# end

# 2nd Iteration

class Hamming
	def self.compute(a,b)
		least = [a,b].min.size
		(least).times.count do |i|
			a[i] != b[i]
		end
	end
end
