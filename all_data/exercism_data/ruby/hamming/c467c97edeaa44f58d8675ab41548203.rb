class DNA

  attr_accessor :dna_string_chars

  def initialize(dna_string)
    self.dna_string_chars = dna_string.chars
  end

  def hamming_distance(other_dna_string)
    distance = 0
    dna_string_chars.each_with_index do |dna_char, i|
      break if other_dna_string[i].nil?
      next if dna_char == other_dna_string[i]
      distance += 1
    end
    distance
  end

end
