class Hamming
  def self.compute(strand1, strand2)
    distance = 0

    0.upto([strand1.length, strand2.length].min-1) do |i|
      distance += (strand1[i] == strand2[i] ? 0 : 1)
    end

    distance
  end
end
