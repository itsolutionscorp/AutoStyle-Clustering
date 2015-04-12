class Hamming

  def compute (dna1, dna2)

    dist = 0

    dna1.chars.each_with_index do |char, i|
      if dna1[i] != dna2[i]
        dist += 1
      end
    end

    return dist
  end

end
