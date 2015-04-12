class Hamming
  def compute(strand1, strand2)
    strand1.chars.reject.with_index { |char, i| char == strand2[i]}.count
  end
end
