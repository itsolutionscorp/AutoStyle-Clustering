class Hamming
  def self.compute dna_strand_a, dna_strand_b
    dna_strand_1, dna_strand_2 = sort_strands(dna_strand_a, dna_strand_b)
    merged_dna = dna_strand_1.chars.zip(dna_strand_2.chars)
    merged_dna.count do |joint|
      joint.first != joint.last
    end
  end

  def self.sort_strands(dna_strand_a, dna_strand_b)
    dna_strand_a.size > dna_strand_b.size &&
      [ dna_strand_b, dna_strand_a ] ||
      [ dna_strand_a, dna_strand_b ]
  end
end
