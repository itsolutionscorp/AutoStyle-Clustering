class Hamming
  def self.compute(strand, other_strand)
    a, b = strand.chars, other_strand.chars
    if a.length < b.length
      a.zip(b).count { |x, y| x != y }
    else
      b.zip(a).count { |x, y| x != y }
    end
  end
end
