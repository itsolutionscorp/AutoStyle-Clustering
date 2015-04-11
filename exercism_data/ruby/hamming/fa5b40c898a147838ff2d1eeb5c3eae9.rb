class Hamming
  def self.compute(strand1, strand2)
    strand1.chars.zip(strand2.chars).count do |strand1_nucleotide, strand2_nucleotide|
      strand1_nucleotide != strand2_nucleotide
    end
  end
end
