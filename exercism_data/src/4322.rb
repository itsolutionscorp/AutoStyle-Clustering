class Hamming
  def compute(strand_a, strand_b)
    length = [strand_a.length, strand_b.length].min
    count = 0
    length.times do |i|
      count += 1 unless strand_a[i] == strand_b[i]
    end
    count
  end
end
