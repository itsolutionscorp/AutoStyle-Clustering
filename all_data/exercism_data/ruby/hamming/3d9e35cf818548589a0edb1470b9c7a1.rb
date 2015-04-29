class Hamming
  def self.compute strand1, strand2

    compare_length = [strand1.length, strand2.length].min

    (0...compare_length).count{|i| strand1[i] != strand2[i]}
  end
end
