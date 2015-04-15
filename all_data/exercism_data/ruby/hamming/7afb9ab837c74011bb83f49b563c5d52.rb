class Hamming
  # calculate the Hamming difference between two DNA strands
  def self.compute(strand_1, strand_2)
    distance = 0
    strand_1.chars.each_with_index do |base_1, i|
      distance += 1 if nucleotide_has_mutation?(base_1, strand_2.chars[i])
    end
    distance
  end

  def self.nucleotide_has_mutation?(char_1, char_2 = nil)
    char_1 != char_2 && char_2
  end
end
