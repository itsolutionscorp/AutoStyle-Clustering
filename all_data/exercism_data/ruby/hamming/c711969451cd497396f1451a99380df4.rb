class Hamming
  def initialize(*strands)
    strands_to_chars = strands.flatten.map { |strand| strand.chars }
    @strand_a, @strand_b = strands_to_chars.sort_by(&:length) 
  end

  def self.compute(*strands)
    hamming = Hamming.new(strands)
    hamming.compute
  end

  def compute
    mutations.compact.length
  end

  private

  def mutations
    genomes.map { |a, b| 1 if is_mutation?(a, b) }
  end

  def genomes
    @strand_a.zip(@strand_b)
  end

  def is_mutation?(a, b)
    a != b
  end
end
