class Hamming
  def compute(first_strand, other_strand)
    strands = [first_strand, other_strand].sort_by(&:length)
    short_strand, long_strand = strands.first, strands.last
    diff, index = 0, 0
    short_strand.chars do |char|
      diff += 1 if char != long_strand[index]
      index += 1
    end
    diff
  end
end
