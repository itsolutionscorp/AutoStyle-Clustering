module Hamming

	@@strand_one = nil
	@@strand_two = nil

	def self.compute(strand_one, strand_two)
		hamming_distance = 0
		self.prepare_strands(strand_one, strand_two)
		return hamming_distance if strands_match
		self.inspect_for_mutations {|found_mutation| hamming_distance += 1 }
		return hamming_distance
	end

	private 
		
		def self.inspect_for_mutations
			@@strand_one.each_index do |nucleic_acid|
				yield	if mutation_detected(@@strand_one[nucleic_acid], @@strand_two[nucleic_acid])
			end
		end

		def self.prepare_strands(strand_one, strand_two)
			@@strand_one = strand_one.split("")
			@@strand_two = strand_two.split("")
		end

		def self.mutation_detected(element_one, element_two)
			element_one != element_two && element_two != nil
		end

		def self.strands_match
			@@strand_one == @@strand_two
		end

end
