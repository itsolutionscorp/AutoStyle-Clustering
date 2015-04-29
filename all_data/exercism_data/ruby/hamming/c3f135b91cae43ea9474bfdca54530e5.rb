class Hamming
  def initialize(strand_1, strand_2)
    @strand_1 = strand_1
    @strand_2 = strand_2
  end

  def self.compute(strand_1, strand_2)
    new(strand_1, strand_2).calculate
  end

  def calculate
    pairs.inject(0) do |count, pair|
      if pair.first == pair.last
        count
      else
        count += 1
      end
    end
  end

  private

  attr_reader :strand_1, :strand_2

  def pairs
    strand_1.chars.map.with_index do |polymer, index|
      [polymer, strand_2.chars[index]]
    end
  end
end
