class DNA

  attr_reader :first_strand

  def initialize(first_strand)
    @first_strand = first_strand
  end

  def hamming_distance(second_strand)
    delta = 0
    (0..(min_strand_length(second_strand)-1)).each do |position|
      delta += 1 unless first_strand[position] == second_strand[position] 
    end
    delta
  end

  def min_strand_length(second_strand)
    if first_strand.length <= second_strand.length
      first_strand.length
    else
      second_strand.length
    end
  end

end
