class DNA
  
  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(other_strand)
    max_possible_mutations_amount = limit(other_strand)
    (@strand.chars.zip other_strand.chars).take(max_possible_mutations_amount).count do |nuclecotide, other_nucleotide|
      nuclecotide != other_nucleotide
    end
  end

  private

    def limit(other_strand)
      [@strand.size, other_strand.size].min
    end

end
