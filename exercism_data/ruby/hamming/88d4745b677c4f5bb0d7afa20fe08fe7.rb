class Hamming
  def self.compute(strand_a, strand_b)
    diff = 0
    length = strand_a.length < strand_b.length ? strand_a.length : strand_b.length
    (0...length).each do |index|
      diff+= 1 if (strand_a[index] != strand_b[index])
    end
    diff
  end
end
