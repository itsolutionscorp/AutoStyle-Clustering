class DNA
  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(strand)
    0.upto(strand_length).count { |base| mutated? strand, base }
  end

  private

  def mutated?(strand, base)
    strand[base] && strand[base] != @strand[base]
  end

  def strand_length
    @strand.size - 1
  end
end
