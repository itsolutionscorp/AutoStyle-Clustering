module Hamming
  def self.compute(a, b)
    [a, b].map(&:length).min.times.select { |i| a[i] != b[i] }.count
  end
end
