class Hamming
	
	def self.compute(first_strand, second_strand)
		@string_difference = 0
		if first_strand.length == second_strand.length
		  string_length = first_strand.length
		  for i in 0..string_length do
            @string_difference += 1 if first_strand.slice(i) != second_strand.slice(i)
			i += 1
		  end
	    else
		  puts "Strings are not of identical length"
		end
		@string_difference
	end
	
end
