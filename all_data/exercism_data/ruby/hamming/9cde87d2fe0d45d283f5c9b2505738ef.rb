class Hamming
  def self.compute(dna_strand, comparision_dna_strand)
    dna_strand_bases = dna_strand.split('')

    dna_strand_bases.each_with_index.reduce(0) do |total, (base, index)|
      total += score_comparision(base, comparision_dna_strand[index])
    end
  end

  def self.score_comparision(base_a, base_b)
    if base_a == base_b || base_b.nil?
      0
    else
      1
    end
  end
end
