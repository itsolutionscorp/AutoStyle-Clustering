class Hamming

	def compute(first_strand, sec_strand)
	  difference = 0
	  first_strand.length <= sec_strand.length ? shortest_length = first_strand.length
                                             : shortest_length = sec_strand.length
	  shortest_length.times do |n|
      difference +=1 if first_strand[n] != sec_strand[n]
	  end
	  difference
	end

end
