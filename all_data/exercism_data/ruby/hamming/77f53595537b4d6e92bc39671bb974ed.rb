class Hamming
  def self.compute(strand1, strand2)
    distance = 0

    max = [ strand1.size, strand2.size ].min
    
    (0...max).each do |i|
      distance += 1 unless na_equal?(strand1[i], strand2[i])
    end

    distance
  end

  def self.na_equal?(na1, na2)
    na1 == na2
  end
end
