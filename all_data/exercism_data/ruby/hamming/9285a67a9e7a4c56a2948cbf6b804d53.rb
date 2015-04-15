class Hamming
  def self.compute(strand1, strand2)
    nucleotide_pairs(strand1, strand2).count do |nucleotide1, nucleotide2|
      nucleotide1 != nucleotide2
    end
  end

  def self.nucleotide_pairs(strand1, strand2)
    nucleotides(strand1).zip(nucleotides(strand2))
  end

  def self.nucleotides(strand)
    strand.each_char
  end
end
