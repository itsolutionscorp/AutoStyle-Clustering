class Hamming
  def compute(dna1, dna2)
    distance = 0
    dna1.split("").zip(dna2.split("")).each do |nucleoid1, nucleoid2|
        if nucleoid1 != nucleoid2
            distance += 1
        end
    end
    return distance
  end
end
