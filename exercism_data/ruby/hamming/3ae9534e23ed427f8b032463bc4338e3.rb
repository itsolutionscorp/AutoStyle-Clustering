class Hamming

  def self.compute(strand1, strand2)
    hamming_distance= 0
    strand1, strand2 = strand2, strand1 if strand1.size > strand2.size
    strand1.chars.each_with_index do |nucleotide, index|
      hamming_distance += (nucleotide <=> strand2[index]).abs
    end
    hamming_distance
  end

end
