class DNA

  attr_reader :first_strand

  def initialize(first_strand)
    @first_strand = first_strand
  end

  def hamming_distance(second_strand)
    delta = 0
    (0...(shared_strand_length(second_strand))).each do |index|
      delta += 1 unless first_strand[index] == second_strand[index]
    end
    delta
  end

  def shared_strand_length(second_strand)
    if first_strand.length <= second_strand.length
      first_strand.length
    else
      second_strand.length
    end
  end

end
