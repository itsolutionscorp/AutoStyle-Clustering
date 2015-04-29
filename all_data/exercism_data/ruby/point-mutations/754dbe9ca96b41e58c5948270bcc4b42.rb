class DNA

  def initialize(dna)
    @dna = dna
  end

  def hamming_distance(comparison_dna)
    dna_chars = @dna.chars.to_a
    comparison_dna_chars = comparison_dna.chars.to_a
    maximum_iterations = [comparison_dna.size,@dna.size].min

    (0..maximum_iterations-1).inject(0) do |memo, index|
      memo += 1 unless dna_chars[index] == comparison_dna_chars[index]
      memo
    end
  end

end
