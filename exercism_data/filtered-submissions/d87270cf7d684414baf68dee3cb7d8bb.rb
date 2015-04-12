class Hamming
  def compute(dna1, dna2)
    total = 0
    dna1.each_char.with_index do |char, index|
      if char == dna2[index]
      	total += 0
      else
      	total += 1
      end
    end
    total
  end
end
