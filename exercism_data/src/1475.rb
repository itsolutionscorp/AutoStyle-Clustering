class Hamming
  def compute(dna1, dna2)
    return nil if (dna1.length != dna2.length)

    total = 0
    dna1.each_char.with_index do |ch, index|
      total += 1 if ch != dna2[index]
    end
    total
  end
end
