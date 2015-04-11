class DNA

  attr_reader :strand # :copy_strand
  def initialize(strand)
    @strand = strand.split('')
  end

  def hamming_distance(copy_strand)
    compare_strands(copy_strand)
  end

  def compare_strands(nucleotide)
    mutations = []
    equalize_length(nucleotide).each_with_index do |protein, index|
      if protein != nucleotide[index]
        mutations << protein
      end
    end
    mutations.count
  end

  def equalize_length(nucleotide)
    strand[0..nucleotide.length-1]
  end

end
