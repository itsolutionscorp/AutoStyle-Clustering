class Hamming
  def self.compare(nucleotide1, nucleotide2)
    if nucleotide1 == nucleotide2
      0
    else
      1
    end
  end

  def self.compute(strand1, strand2)
    strand_length = strand1.length
    distance = 0
    strand_length.times do |num|
      distance += self.compare(strand1[num], strand2[num])
    end
    return distance
  end
end
