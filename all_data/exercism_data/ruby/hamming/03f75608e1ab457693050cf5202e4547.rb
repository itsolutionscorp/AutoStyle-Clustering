class Hamming
  def initialize(*strands)
    @strand_a, @strand_b = strands.flatten.map { |strand| strand.chars } 
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
    genomes = @strand_a.zip(@strand_b)
    genomes.map { |genome| 1 if is_mutation?(genome) }
  end

  def is_mutation?(genome)
    return false unless genome.compact.length == 2
    genome[0] != genome[1]
  end
end
