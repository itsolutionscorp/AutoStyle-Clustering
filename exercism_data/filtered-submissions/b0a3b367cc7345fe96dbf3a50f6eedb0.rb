class Hamming
  def compute(strand, other_strand)
    strand = strand.to_s.chars
    other_strand = other_strand.to_s.chars

    max_iterations = [strand.size, other_strand.size].min

    (0...max_iterations).each_with_index.count do |item, index|
      strand[index] != other_strand[index]
    end
  end
end
