class Hamming
	def self.compute(str1, str2)
		test = check_length(str1, str2)
		complete_hamming_distance_of_for_small_strand(str1,str2) if test == 0
	end

	def self.check_length(str1, str2)
		str1.length - str2.length
	end

	def self.complete_hamming_distance_of_for_small_strand(str1,str2)
		str1_a = str1.split('')
		str2_a = str2.split('')
		c = str1_a.zip str2_a
		c.map{|x|x[0].ord-x[1].ord}.count{|c| c != 0}
	end
end
