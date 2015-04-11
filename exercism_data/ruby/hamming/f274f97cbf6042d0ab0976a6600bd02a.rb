class DNA

  attr_reader :strand, :copy_strand
  def initialize(strand)
    @strand = strand.split('')
  end

  def hamming_distance(copy_strand)
    @copy_strand = copy_strand
    compare_strands
  end

  def compare_strands
    mutations = []
    equalize_length.each_with_index do |protein, index|
      if protein != copy_strand[index]
        mutations << protein
      end
    end
    mutations.count
  end

  def equalize_length
    strand[0..copy_strand.length-1]
  end

end
