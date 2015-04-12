class Hamming

  def compute(strand_1, strand_2)
    index = 0
    hamming_difference = 0
    while index < ([strand_1.length, strand_2.length].min)
      hamming_difference += 1 if strand_1[index] != strand_2[index]
      index += 1
    end
    return hamming_difference
  end

end
