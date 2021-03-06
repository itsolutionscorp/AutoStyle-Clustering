class DNA

  attr_reader :strand
  def initialize(strand)
    @strand = strand.split('')
  end

  def hamming_distance(other_strand)
    compare_strands(other_strand)
  end

  def compare_strands(nucleotide)
    mutations = []
    equalize_length(nucleotide).each_with_index do |strand, index|
      if strand != nucleotide[index]
        mutations << strand
      end
    end
    mutations.count
  end

  def equalize_length(nucleotide)
    strand[0..nucleotide.length-1]
  end

end
