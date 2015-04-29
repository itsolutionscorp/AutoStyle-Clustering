class Hamming
  class << self
    def compute(dna, mutated_dna)
      new(dna, mutated_dna).distance
    end
  end

  def initialize(dna, mutated_dna)
    length = [dna.length, mutated_dna.length].min

    @dna = dna.slice(0, length).chars
    @mutated_dna = mutated_dna.slice(0, length).chars
  end

  def distance
    @distance ||= mutated_pairs.count
  end

  private

  def mutated_pairs
    @pairs ||= @dna.zip(@mutated_dna).select { |a, b| a != b }
  end
end
