class Hamming
  class << self
    def compute(dna, mutated_dna)
      dna, mutated_dna = fix_length dna, mutated_dna
      new(dna, mutated_dna).distance
    end

    private

    def fix_length(a, b)
      length = a.length < b.length ? a.length : b.length
      [a.slice(0, length), b.slice(0, length)]
    end
  end

  def initialize(dna, mutated_dna)
    unless dna.length == mutated_dna.length
      raise ValueError, 'must be same length'
    end

    @dna = dna.split('')
    @mutated_dna = mutated_dna.split('')
  end

  def mutated_pairs
    @pairs ||= @dna.zip(@mutated_dna).select { |a, b| a != b }
  end

  def distance
    @distance ||= mutated_pairs.count
  end
end
