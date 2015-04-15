class Hamming
  def self.compute(strand1, strand2)
    distance = 0

    [strand1.length, strand2.length].min.times do |i|
      distance += 1 unless strand1[i] == strand2[i]
    end

    distance
  end
end
