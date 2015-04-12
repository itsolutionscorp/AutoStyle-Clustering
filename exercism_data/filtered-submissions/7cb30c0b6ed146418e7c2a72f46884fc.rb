class Hamming
	def compute(s1, s2)
		return s1.split("").zip(s2.split("")).map{ |a,b| a == b}.count(false)
	end
end
