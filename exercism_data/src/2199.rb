class Hamming
  def compute(strand1, strand2)
    return 0 if strand1 === strand2
    limit = [strand1.length, strand2.length].min

    distance = 0
    for i in 0..limit-1
      distance = distance + 1 if strand1[i] != strand2[i]
    end

    distance
  end
end
