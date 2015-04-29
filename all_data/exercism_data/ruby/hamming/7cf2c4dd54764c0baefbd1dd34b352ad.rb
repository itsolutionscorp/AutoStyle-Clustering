class DNA
  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(strand)
    0.upto(strand_length).each_with_object([]) do |base, mutations| 
      mutations.push base if mutation_for strand, base 
    end.count
  end

  private

  def mutation_for(strand, base)
    strand[base] && strand[base] != @strand[base]
  end

  def strand_length
    @strand.size - 1
  end
end
