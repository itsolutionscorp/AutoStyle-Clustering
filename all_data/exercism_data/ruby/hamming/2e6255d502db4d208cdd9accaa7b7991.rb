class DNA
  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(other_strand)
    distance = 0
    min_strand_size = [@strand.size, other_strand.size].min
    min_strand_size.times do |index|
      if @strand[index] != other_strand[index]
        distance += 1
      end
    end
    distance
  end
end
