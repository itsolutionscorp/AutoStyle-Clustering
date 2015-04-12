class Hamming
  def compute(strand1, strand2)
    distance = 0

    0.upto([strand1.length, strand2.length].min-1) do |i|
      distance += 1 unless strand1[i] == strand2[i]
    end

    distance
  end
end
