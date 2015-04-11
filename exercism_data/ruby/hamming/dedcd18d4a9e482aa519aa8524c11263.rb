class Hamming
  def self.compute(strand1, strand2)
    shortest = [strand1, strand2].min_by(&:length)
    (0...shortest.length).count { |i| strand1[i] != strand2[i] }
  end
end
