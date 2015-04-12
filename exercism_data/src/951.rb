class Hamming
	def compute(a,b)
		diffs = 0
		a.split('').each_with_index do |strand, index|
			diffs += 1 if b[index] && strand != b[index]
		end
		diffs
	end


end
