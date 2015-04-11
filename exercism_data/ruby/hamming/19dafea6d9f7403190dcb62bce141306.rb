class Hamming
  def self.compute(strand1, strand2)
    count = 0
    if strand1.length == strand2.length
    strand1.length.times { |x| count += 1 unless strand1[x] == strand2[x]}
    end
    count
  end
end
