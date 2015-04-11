class DNA
  attr_reader :strand, :homologous_strand

  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(homologous_strand)
    0.upto([strand.length, homologous_strand.length].min - 1).inject(0) do |differences_count, index|
      if nucleic_acids_differ?(index, homologous_strand)
        differences_count += 1
      end
      differences_count
    end
  end

  private
  def nucleic_acids_differ?(index, homologous_strand)
    strand[index].to_s != homologous_strand[index].to_s
  end
end
