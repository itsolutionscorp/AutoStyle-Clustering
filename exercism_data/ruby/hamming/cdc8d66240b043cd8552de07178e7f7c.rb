class Hamming
  def self.compute(dna_strand_1, dna_strand_2)
    dna_strand_1 == dna_strand_2 ? 0 : count_differences(dna_strand_1, dna_strand_2)
  end

  private
  def self.count_differences(dna_strand_1, dna_strand_2)
    dna_character_position = 0
    dna_character_length = set_length( dna_strand_1, dna_strand_2 )
    hamming_distance = 0

    while dna_character_position != dna_character_length
      dna_strand_1[dna_character_position] == dna_strand_2[dna_character_position] ? hamming_distance += 0 : hamming_distance += 1

      dna_character_position += 1
    end

    hamming_distance
  end

  def self.set_length( dna_strand_1, dna_strand_2)
    dna_strand_1.length > dna_strand_2.length ? dna_strand_2.length : dna_strand_1.length
  end
end
