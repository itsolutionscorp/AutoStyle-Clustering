class DNA
  
  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(other_strand)
    max_possible_mutations_amount = limit(other_strand)
    mutations = 0
    (@strand.chars.zip other_strand.chars).take(max_possible_mutations_amount).each do |nuclecotide, other_nucleotide|
      mutations += 1 if nuclecotide != other_nucleotide
    end
    mutations
  end

  private

    def limit(other_strand)
      [@strand.size, other_strand.size].min
    end

end
