class Hamming
	def self.compute a, b
		return 0 if a == b
		total = 0
		a.each_char.with_index do |c, i|
			total += 1 if(c != b[i])
		end
		total
	end
end
