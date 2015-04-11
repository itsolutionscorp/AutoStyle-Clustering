class Hamming
  def self.compute(strand1, strand2)
    common_length = [strand1, strand2].map(&:size).min
    common_length.times.count { |i| strand1[i] != strand2[i] }
  end
end
