class DNA
  attr_reader :strand, :homologous_strand

  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(homologous_strand)
    nucleic_acid_indexes(homologous_strand).inject(0) do |differences_count, nucleic_acid_index|
      if nucleic_acids_differ?(nucleic_acid_index, homologous_strand)
        differences_count += 1
      end
      differences_count
    end
  end

  private

  def nucleic_acids_differ?(nucleic_acid_index, homologous_strand)
    strand[nucleic_acid_index].to_s != homologous_strand[nucleic_acid_index].to_s
  end

  def nucleic_acid_indexes(homologous_strand)
    0.upto([strand.length, homologous_strand.length].min - 1)
  end
end
