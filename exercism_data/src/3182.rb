class Hamming

  def compute(strand_a, strand_b)
    distance = 0
    strand_a.chars.each_index do |i|
      distance += 1 if strand_a[i] == strand_b[i]
    end
    distance
  end
end
