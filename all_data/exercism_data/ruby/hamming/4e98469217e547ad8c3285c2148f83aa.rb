class Hamming
  def self.compute(strand_1, strand_2)
    dif = 0
    [strand_1.length, strand_2.length].min.times do |i|
        dif += 1 unless strand_1[i] == strand_2[i]
    end
      return dif
  end
end
