class Hamming
  def self.compute(one,two)
    distance = 0
    for i in 0..one.length
      distance += 1 if one[i] != two[i]
    end
    distance
  end
end
