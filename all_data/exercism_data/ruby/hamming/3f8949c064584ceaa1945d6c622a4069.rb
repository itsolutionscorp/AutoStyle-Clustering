class Hamming
  def self.compute(strandone, strandtwo)
    distance = 0
    strlen = strandone.length
    for i in 0..strlen
      distance = distance + 1 if strandone[i] != strandtwo[i]
    end
    return distance
  end
end
