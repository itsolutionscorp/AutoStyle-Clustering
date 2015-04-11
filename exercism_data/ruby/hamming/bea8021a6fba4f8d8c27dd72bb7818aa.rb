class DNA
  attr_reader :strand

  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(homologous_strand)
    nucleic_acid_indexes(homologous_strand).select do |nucleic_acid_index|
      nucleic_acids_differ?(nucleic_acid_index, homologous_strand)
    end.count
  end

  private

  def nucleic_acids_differ?(nucleic_acid_index, homologous_strand)
    strand[nucleic_acid_index].to_s != homologous_strand[nucleic_acid_index].to_s
  end

  def nucleic_acid_indexes(homologous_strand)
    0.upto([strand.length, homologous_strand.length].min - 1)
  end
end
