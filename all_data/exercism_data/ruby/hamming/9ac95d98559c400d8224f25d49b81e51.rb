class Hamming

  def self.compute (strand_a, strand_b)
    if strand_a.length != strand_b.length
      puts "Sequences must be of equal length to define their Hamming distance."
    end
    distance = 0
    nucleotide = Array(0..strand_a.length - 1)
    nucleotide.each do |n|
      if
        strand_a[n] != strand_b[n]
        distance += 1
      end
    end
    distance
  end
end
