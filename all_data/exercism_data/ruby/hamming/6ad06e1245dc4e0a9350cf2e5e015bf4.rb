module Hamming
  def self.compute(first, second)
    first, second = equalize_lengths(first, second)
    first.chars.zip(second.chars).count { |tuple| tuple[0] != tuple[1] }
  end

  def self.equalize_lengths(first, second)
    shorter = [first.length, second.length].min
    [first[0..shorter-1], second[0..shorter-1]]
  end
end
