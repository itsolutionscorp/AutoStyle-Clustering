class Hamming
  def self.compute(strand1, strand2)
    compare_length = [strand1.length, strand2.length].min
    compare_length.times.count{|index| strand1[index] != strand2[index]}
  end
end
