class Hamming
  def compute(strand, other_strand)
    strand.each_char.with_index.count do |char, index|
      char != other_strand[index]
    end
  end
end
