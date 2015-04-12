class Hamming

	def compute (dna1, dna2)
		count = 0
		f1 = Array.new
		f2 = Array.new

		dna1.each_char {|d1| f1.push d1}
		dna2.each_char {|d2| f2.push d2}
	
		[f1,f2].transpose.each do |d1, d2|
			if d1 != d2
				count += 1
			end
		end

		return count
	end

end
