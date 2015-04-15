module Hamming
  def self.compute(strand_a, strand_b)
    distance = 0
    point_pairs(strand_a, strand_b) do |a, b|
      distance += 1 if a != b
    end
    distance
  end

  private

  def self.point_pairs(a, b)
    a.split('').zip(b.split('')) do |a, b|
      yield a, b unless a.nil? || b.nil?
    end
  end
end
