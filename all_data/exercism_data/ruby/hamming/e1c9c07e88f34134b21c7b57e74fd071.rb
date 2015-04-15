class Hamming
  def self.compute(strand0, strand1)
    len0 = strand0.length
    len1 = strand1.length
    distance = 0
    for i in 0..[len0, len1].min - 1 do
      distance += 1 unless strand0[i] == strand1[i]
    end
    distance
  end
end
