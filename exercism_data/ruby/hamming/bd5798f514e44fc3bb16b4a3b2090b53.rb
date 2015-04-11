class Hamming
  
  def self.compute(strand, other_strand)
    sum = 0
    while strand.length > 0 and other_strand.length > 0 do
      a, b = slice_leading_char strand, other_strand
      if a != b
        sum += 1
      end
    end
    sum
  end

  def self.slice_leading_char(strand, other_strand)
    return strand.slice!(0), other_strand.slice!(0)
  end

end
