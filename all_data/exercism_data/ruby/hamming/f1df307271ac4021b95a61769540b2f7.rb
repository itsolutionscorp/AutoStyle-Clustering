class Hamming
  def self.compute(strand1, strand2)
    distance = 0
    if strand1.length > strand2.length
      strand1 = strand1.slice 0, strand2.length
    end
    strand1.split('').each_with_index do |c, i|
      if strand1[i] != strand2[i]
        distance += 1
      end
    end
    distance
  end
end
