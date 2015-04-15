class Hamming
	def self.compute(str_a, str_b)
		result = 0
		min_length = [str_a.length, str_b.length].min

		min_length.times do |i|
			result += 1 if str_a[i] != str_b[i]
		end

		result
	end
end
