class Hamming
  def compute (dna_1, dna_2)
    shortest = dna_1.length <= dna_2.length ? dna_1.length : dna_2.length

    distance = 0
    shortest.times do |i|
      distance += 1 if dna_1[i] !=  dna_2[i]
    end
    return distance
  end

end
