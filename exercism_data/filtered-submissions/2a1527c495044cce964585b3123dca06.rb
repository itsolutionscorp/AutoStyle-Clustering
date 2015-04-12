class Hamming
  def compute(strand, other_strand)
    strand = strand.scan(/\w/)
    other_strand = other_strand.scan(/\w/)
    strand.each.with_index.count do |char, index|
      char != other_strand[index]
    end
  end
end
