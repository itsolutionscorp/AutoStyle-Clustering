class Hamming
  def compute(dna_strand_a, dna_strand_b)
    i = 0
    hamming_distance = 0
    dna_strand_a.each_char do |bp_a|
      bp_b = dna_strand_b.byteslice(i)
      hamming_distance += 1 if bp_b != bp_a && bp_b != nil
      i += 1
    end

    hamming_distance
  end
end
