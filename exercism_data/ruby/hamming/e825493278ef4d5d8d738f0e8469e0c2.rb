class Hamming
  def self.compute(strand1, strand2)
    dif = 0
    strand1.length.times do |index|
      if strand1[index] && strand2[index]
        dif += 1 unless strand1[index] == strand2[index]
      end
    end
    dif
  end
end
