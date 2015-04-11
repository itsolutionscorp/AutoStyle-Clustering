class Hamming
	def self.compute(first, second)
		result = 0
		first.chars.zip(second.chars).each do |f, s|
			if (f != nil && s != nil && f != s)
				result += 1
			end
		end
		return result
	end
end
