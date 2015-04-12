class Hamming

  def compute (dna1, dna2)
    if dna1.length != dna2.length
      raise "unequal length! #{dna1.length} chars to #{dna2.length} chars"
    end


    dist = 0

    dna1.chars.each_with_index do |char, i|
      if dna1[i] != dna2[i]
        dist += 1
      end
    end

    return dist
  end

end
