class Hamming
  def self.compute(strand_a, strand_b)
    strand_a = strand_a.chars
    strand_b = strand_b.chars

    strand_a.zip(strand_b).count do |nucleotide_a, nucleotid_b|
      nucleotide_a != nucleotid_b
    end
  end
end
