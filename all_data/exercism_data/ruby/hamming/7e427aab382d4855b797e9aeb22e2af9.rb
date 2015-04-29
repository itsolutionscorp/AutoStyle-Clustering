class Hamming
  def self.compute(strand1, strand2)
    length = [strand1.length, strand2.length].sort.first
    diff = 0

    for i in (0..length-1)
      diff += 1 unless strand1[i] == strand2[i]
    end

    diff
  end
end
