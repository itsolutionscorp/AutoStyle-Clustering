class Hamming

  def compute(dna, comparison_dna)
    dna_chars = dna.chars.to_a
    comparison_dna_chars = comparison_dna.chars.to_a
    maximum_iterations = [comparison_dna.size,dna.size].min

    (0..maximum_iterations-1).each_with_index.count do |memo, index|
      dna_chars[index] != comparison_dna_chars[index]
    end
  end

end
