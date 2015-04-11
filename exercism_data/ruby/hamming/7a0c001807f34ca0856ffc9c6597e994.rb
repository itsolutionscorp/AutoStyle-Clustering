class Hamming
	def self.compute(first_strand, second_strand)
		distance = 0;
		first_strand, second_strand = [first_strand.chars, second_strand.chars].sort_by { |x| x.length }
		first_strand.each_with_index do |ch, i|
			distance += 1 if second_strand[i] != ch
		end
		distance
	end
end
