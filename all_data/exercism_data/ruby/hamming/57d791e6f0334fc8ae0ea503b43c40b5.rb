class Hamming
  def self.compute(strand, other_strand)
    strand       = strand.chars
    other_strand = other_strand.chars

    pairs        = strand.zip(other_strand).first(other_strand.length)

    pairs.count do |left, right|
      left != right
    end
  end
end
