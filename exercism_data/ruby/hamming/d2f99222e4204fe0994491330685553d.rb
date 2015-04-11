class Hamming

  def self.compute(strand_a, strand_b)
    difference = 0
    strand_a.split('').each_with_index do |char, i|
      if strand_a[i] != strand_b[i]
        difference += 1
      end
    end
    return difference
  end

end
