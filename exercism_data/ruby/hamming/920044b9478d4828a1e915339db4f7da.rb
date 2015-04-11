#*****
#  method compute
#  test two dna strand strings for differences in their sequence
#  strings are of unknown, potnetialy varying length- proceed no fruther then shortest
#  return the number of differences between the two dna strands aka hamming distance
#  
#   Sample strings with a hamming distance of 7
#   GAGCCTACTAACGGGAT
#   CATCGTAATGACGGCCT
#   ^ ^ ^  ^ ^    ^^
#*****

class Hamming

	#for testing add self. to method
	def self.compute(nucleotied_a, nucleotied_b)
		
		@dna_strand_one = []
		@dna_strand_two = []
		@hamming_distance = 0

		nucleotied_a.each_char { |nucleotoid| @dna_strand_one << [nucleotoid] }
		nucleotied_b.each_char { |nucleotoid| @dna_strand_two << [nucleotoid] }
		
		@enum_dna_strand_one = @dna_strand_one.to_enum
		@enum_dna_strand_two = @dna_strand_two.to_enum
		
		loop do
			
			if @enum_dna_strand_one.peek != @enum_dna_strand_two.peek
			   @hamming_distance += 1
			end
			
			@enum_dna_strand_one.next
			@enum_dna_strand_two.next
            
		end
		
		return @hamming_distance
	end

end
