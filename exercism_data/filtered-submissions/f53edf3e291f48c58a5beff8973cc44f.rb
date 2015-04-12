class Hamming
  def compute(dna1, dna2)
    fail ArgumentError, 'Argument lengths are not equal' unless dna1.size == dna2.size
    total = 0
    dna1.each_char.with_index do |char, index|
      total += 1 unless char == dna2[index]
    end
    total
  end
end
