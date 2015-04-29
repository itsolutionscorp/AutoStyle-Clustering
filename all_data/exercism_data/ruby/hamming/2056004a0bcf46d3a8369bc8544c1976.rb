class Hamming
  def self.compute(a, b)
    hamming = 0
    (0..shorter_strand_size(a, b) - 1).each do |i|
      hamming +=1 unless a[i] == b[i]
    end
    hamming
  end

  def self.shorter_strand_size(a, b)
    a.size < b.size ? a.size : b.size
  end
end
