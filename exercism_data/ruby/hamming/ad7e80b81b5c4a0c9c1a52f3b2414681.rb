class Hamming

  def no_difference_between_identical_strands
  	self.compute('A', 'A')
  end

  def complete_hamming_distance_of_for_single_nucleotide_strand
  	self.compute('A', 'G')
  end

  def complete_hamming_distance_of_for_small_strand
  	self.compute('AG', 'CT')
  end

  def small_hamming_distance
  	self.compute('AT', 'CT')
  end

  def small_hamming_distance_in_longer_strand
  	self.compute('GGACG', 'GGTCG')
  end

  def nonunique_characters_within_first_strand
  	self.compute('AGA', 'AGG')
  end

  def nonunique_characters_within_second_strand
  	self.compute('AGG', 'AGA')
  end

  def large_hamming_distance
  	self.compute('GATACA', 'GCATAA')
  end

  def hamming_distance_in_very_long_strand
  	self.compute('GGACGGATTCTG', 'AGGACGGATTCT')
  end

  def self.compute(*args)
		arg_length = args[0].length
		loop_count = 0
		assert_count = 0
		while loop_count <= arg_length
			loop_count = loop_count + 1
			array_number = loop_count - 1
			if args[0][array_number] != args[1][array_number]
				assert_count = assert_count + 1
			end
		end
		return assert_count
  end
end
