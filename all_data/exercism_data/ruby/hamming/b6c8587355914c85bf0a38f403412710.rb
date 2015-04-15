class Hamming
  def self.compute dna_strand_a, dna_strand_b
    slice_limit = calculate_slice_limit(dna_strand_a, dna_strand_b)

    dna_strand_a_chars = dna_strand_a[0..slice_limit].chars
    dna_strand_b_chars = dna_strand_b[0..slice_limit].chars

    merged_dna = dna_strand_a_chars.zip dna_strand_b_chars

    merged_dna.count do |joint|
      joint.first != joint.last
    end
  end

  def self.calculate_slice_limit dna_strand_a, dna_strand_b
    if dna_strand_a.size > dna_strand_b.size
      dna_strand_b.size - 1
    else
      dna_strand_a.size - 1
    end
  end
end
