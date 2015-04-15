class Hamming
  def self.compute(strand_1, strand_2)
    hamming_distance = 0
    strand_1.length.times do |index|
        nucleotide_1 = strand_1[index]
        nucleotide_2 = strand_2[index]
        hamming_distance = hamming_distance + 1 if nucleotide_1 != nucleotide_2
    end
    return hamming_distance
  end
end
