class Hamming

  def compute(strand_1, strand_2)
    distance = 0
    length = strand_1.length
    for i in 0..length
      distance+= 1 if strand_1[i] != strand_2[i]
    end
    distance
  end
end
