class DNA

  attr_reader :first_strand

  def initialize(first_strand)
    @first_strand = first_strand
  end

  def hamming_distance(second_strand)
    delta = shared_strand_range(second_strand).inject(0) { |mem, index|
      first_strand[index] != second_strand[index] ? mem + 1 : mem
    }
  end

  def shared_strand_range(second_strand)
    (0...[first_strand.length, second_strand.length].min)
  end

end
