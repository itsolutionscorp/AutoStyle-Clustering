class Hamming
	def self.compute(a, b)
		return 0 if a.nil? || b.nil?

		used_a = a
		used_b = b
		if a.length > b.length
			used_a = b
			used_b = a
		end

		diff = 0
		used_a.chars.to_a.each_with_index do |f, i|
			diff += 1 if f != used_b[i]
		end
		diff
	end
end
