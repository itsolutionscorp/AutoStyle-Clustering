class Hamming
	def self.compute(str1, str2)
		(0...([str1.length, str2.length].min)).count { |n| str1[n] != str2[n]}
	end
end
