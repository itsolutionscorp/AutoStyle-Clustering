class Hamming
  def self.compute(original_dna, modified_dna)
    return 0 if (original_dna == modified_dna)
    calc_min_point_mutations(original_dna, modified_dna)
  end

  private

  def self.calc_min_point_mutations(original_dna, modified_dna)
    (0..shorter_length(original_dna, modified_dna)).inject do |distance, n|
      distance += 1 if original_dna[n-1] != modified_dna[n-1]
      distance
    end
  end

  def self.shorter_length(original_dna, modified_dna)
    original_dna.size < modified_dna.size ? original_dna.size : modified_dna.size
  end
end
