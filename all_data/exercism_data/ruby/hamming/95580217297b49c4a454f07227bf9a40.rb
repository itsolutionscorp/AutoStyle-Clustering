class Hamming

	def self.compute(arg1, arg2)
		compare(arg1, arg2).inject{|sum, n| sum + n}
	end	

	def self.compare(arg1, arg2)
		(0...(arg1.size)).map do |n|
			(arg1[n] <=> arg2[n]).abs
		end
	end

end
