class Hamming
  def self.compute(*args)
    self.new(*args).compute
  end

  def initialize(strand_a, strand_b)
    @strand_a, @strand_b = strand_a, strand_b
  end

  def compute
    matching_base_pairs.reduce(0) do |count, (base_a, base_b)|
      base_a == base_b ? count : count + 1
    end
  end

  private

  attr_reader :strand_a, :strand_b

  def matching_base_pairs
    strand_a.split('').zip(strand_b.split('')).
      reject {|(a, b)| a.nil? || b.nil? }
  end
end
