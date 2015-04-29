class Hamming
  def self.compute(strand1, strand2)
    return 0 if strand1 == strand2
    dist = 0
    [strand1.length, strand2.length].min.times.each do |n|
      dist += 1 if strand1[n] != strand2[n]
    end
    dist
  end
end
