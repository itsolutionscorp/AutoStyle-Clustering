module Hamming
  def self.compute (a, b)
    [a.size, b.size]
      .min
      .times
      .select { |i| a[i] != b[i] }
      .count
  end
end
