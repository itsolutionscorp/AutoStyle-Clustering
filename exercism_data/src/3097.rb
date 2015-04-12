class Hamming
  def compute(strand_a, strand_b)
    hamming_distance = 0
    [strand_a.length, strand_b.length].min.times do |index|
      hamming_distance += 1 unless strand_a[index] == strand_b[index]
    end
    hamming_distance
  end
end
