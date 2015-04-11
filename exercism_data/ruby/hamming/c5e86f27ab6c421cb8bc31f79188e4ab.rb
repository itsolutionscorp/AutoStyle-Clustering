class DNA
  attr_reader :dna

  def initialize(dna)
    @dna = dna
  end

  def min_index(dna_compare)
    [dna_compare.length, dna.length].min - 1
  end

  def hamming_distance(dna_compare)
    distance = 0
    characters = dna_compare[0..min_index(dna_compare)].chars
    characters.each_with_index do |char, index|
      distance += 1 if char != dna[index]
    end

    distance
  end
end
