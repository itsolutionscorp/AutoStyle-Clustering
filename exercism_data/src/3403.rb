class Hamming
  def compute(strand_a, strand_b)
    distance = 0
    strand_a.length.times do |index|
      break unless strand_b[index]
      distance += 1 if strand_a[index] != strand_b[index]
    end
    distance
  end
end
