class Hamming

	def self.compute one, another
		result = 0
		[one.size, another.size].min.times do |i|
			result += 1 if one[i] != another[i]
		end
		result
	end
end
