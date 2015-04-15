class Hamming
	def self.compute(str1, str2)
		(0...common_length(str1, str2)).count {|i|
			str1[i] != str2[i]}
	end

	private

	def self.common_length(str1, str2)
		[str1.length, str2.length].min
	end

end

