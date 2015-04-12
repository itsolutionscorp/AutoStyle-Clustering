class Hamming
  def compute(strand_a,strand_b)
    dist = nil
    if strand_a.length == strand_b.length
      dist = 0
      (0..strand_b.length).each do |i|
        dist += 1 if strand_a[i] != strand_b[i]
      end
    end
    dist
  end
end
