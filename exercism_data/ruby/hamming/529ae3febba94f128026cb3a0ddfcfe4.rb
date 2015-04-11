class DNA
  def initialize(dna_string)
    @dna_string = dna_string
  end

  def hamming_distance(dna_string)
    check_length = [@dna_string.length, dna_string.length].min
    (0...check_length).count {|index| @dna_string[index] != dna_string[index] }
  end
end
