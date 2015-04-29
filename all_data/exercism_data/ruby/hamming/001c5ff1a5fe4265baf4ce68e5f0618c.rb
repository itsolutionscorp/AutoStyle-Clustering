class DNA
  def initialize(strand)
    @my_strand = strand
  end

  def hamming_distance(strand)
    match_nucleotides(strand).count do |nucleotides|
      nucleotides.all? and nucleotides.first != nucleotides.last
    end
  end

  private

  attr_reader :my_strand

  def match_nucleotides(strand)
    my_strand.chars.zip(strand.chars)
  end
end
