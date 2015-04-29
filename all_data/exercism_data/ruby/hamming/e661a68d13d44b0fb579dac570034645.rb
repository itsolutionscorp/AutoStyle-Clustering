module Hamming
  def self.compute(strand_one, strand_two)
    min_length = [strand_one, strand_two].map(&:length).min
    min_length.times.count { |i| strand_one[i] != strand_two[i] }
  end
end
