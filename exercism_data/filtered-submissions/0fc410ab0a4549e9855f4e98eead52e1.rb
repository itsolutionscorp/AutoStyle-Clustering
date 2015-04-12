class Hamming
  def compute(dna_first, dna_second)
    count = 0
    dna_first.chars.each_with_index do |symbol, index|
      count += 1 if (symbol != dna_second[index]) && (dna_second[index] != nil)
    end
    count
  end
end
