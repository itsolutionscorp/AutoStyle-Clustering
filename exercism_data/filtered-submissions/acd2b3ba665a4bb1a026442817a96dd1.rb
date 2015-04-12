class Hamming
	def compute(strand_one,strand_two)
		count = [strand_one.length, strand_two.length].min
		difference = 0
		(0...count).each do |i|
			difference += 1 unless strand_one[i] == strand_two[i]
		end
		
		difference
	end

end
