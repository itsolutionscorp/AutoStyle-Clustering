class Hamming
  def compute(dna1, dna2)
    sum = 0

    dna1 = dna1[0...dna2.length] if dna1.length > dna2.length

    dna1.split('').each.with_index do |elem, index|
      sum += 1 if elem != dna2[index]
    end

    sum
  end
end
