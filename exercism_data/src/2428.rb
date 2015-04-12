class Hamming
	def compute(s1, s2)
		min_length = [s1.length, s2.length].min - 1
		s1 = s1[0..min_length]
		s2 = s2[0..min_length]
		s3 = s1.split("").zip(s2.split(""))
		sum = 0
		s3.each do |pos1, pos2|
			sum += 1 if pos1 != pos2
		end
		sum
	end
end
