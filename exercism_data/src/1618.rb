class Hamming
  def compute(base_dna_strand, comparison_dna_strand)
    base_dna = base_dna_strand.chars
    comparison_dna = comparison_dna_strand.chars

    differences = base_dna.zip(comparison_dna).select do |base, comparison|
      base != comparison
    end

    differences.count
  end
end
