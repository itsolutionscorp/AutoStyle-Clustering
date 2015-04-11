class DNA
  def initialize(strand)
    @my_strand = strand
  end

  def hamming_distance(strand)
    pair_up_nucleotides(strand).count do |nucleotides|
      add_distance? nucleotides
    end
  end

  private

  attr_reader :my_strand

  def pair_up_nucleotides(strand)
    my_strand.chars.zip(strand.chars)
  end

  def add_distance?(nucleotides)
    nucleotides.all? and nucleotides.first != nucleotides.last
  end
end
