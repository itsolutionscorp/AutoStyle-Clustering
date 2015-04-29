class Hamming

  def self.compute(dna_a, dna_b)
    new(dna_a, dna_b).compute
  end

  attr_reader :dna_a, :dna_b

  def initialize(dna_a, dna_b)
    @dna_a, @dna_b = *[dna_a, dna_b].map(&:to_s).sort_by(&:size)
  end

  def compute
    dna_a.chars
         .each_with_index
         .map { |nucleotid, i| dna_b[i] == nucleotid ? 0 : 1 }
         .reduce(&:+)
         .to_i
  end

end
