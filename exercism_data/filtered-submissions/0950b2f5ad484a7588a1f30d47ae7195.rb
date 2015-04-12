class Hamming
  def compute(dna1, dna2)
    hamming_distance = 0
    count = 0
    dna1.each_char do |nucleotide|
      hamming_distance += 1 if nucleotide != dna2[count]
      count += 1
    end
    hamming_distance
  end
end
