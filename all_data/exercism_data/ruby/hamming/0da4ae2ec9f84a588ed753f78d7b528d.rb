class Hamming
  def self.compute(dna_1, dna_2)
    new(dna_1, dna_2).count_differences
  end

  attr_reader :dna_1, :dna_2
  def initialize(dna_1, dna_2)
    @dna_1, @dna_2 = dna_1, dna_2
  end

  def count_differences
    each_index { |i| !equal_at?(i) }
  end

  private
  def each_index(&block)
    index_range.inject(0) { |sum, i| yield(i) ? sum + 1 : sum } 
  end

  def max_dna_length
    [dna_1, dna_2].map(&:length).max
  end 

  def index_range
    (0...max_dna_length)
  end

  def equal_at?(index)
    dna_1[index] == dna_2[index]
  end
end
