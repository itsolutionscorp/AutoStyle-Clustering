class Hamming
	def compute a, b
		return nil if(a.size != b.size)
		return 0 if(a == b)
		total = 0
		a.each_char.with_index do |char, index|
			total += 1 if(char != b[index])
		end
		total
	end
end
