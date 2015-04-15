class Hamming
	def self.compute(str1, str2)
		str1.slice(0,str2.length).chars.zip(str2.chars).delete_if{|x, y| x == y}.size
	end
end
