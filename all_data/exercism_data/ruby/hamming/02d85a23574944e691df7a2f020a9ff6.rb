class Hamming

  def self.compute (strand_a, strand_b)
    distance = 0
    nucleotide = Array(0..strand_a.length - 1)
    nucleotide.each do |n|
      if
        strand_a[n] == strand_b[n]
        distance += 0
      else
        distance += 1
      end
    end
    distance
  end
end
