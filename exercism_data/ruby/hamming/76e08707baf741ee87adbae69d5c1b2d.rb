class DNA

  attr_reader :strand
  def initialize(strand)
    @strand = strand.split('')
  end

  def hamming_distance(other_strand)
    compare_strands(other_strand)
  end

  def compare_strands(nucleotides)
    mutations = []
    equalize_length(nucleotides).each_with_index do |nucleotide, index|
      if nucleotide != nucleotides[index]
        mutations << nucleotides
      end
    end
    mutations.count
  end

  def equalize_length(nucleotides)
    strand[0..nucleotides.length-1]
  end

end
