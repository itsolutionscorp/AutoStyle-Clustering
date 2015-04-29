class DNA 
  def initialize(nucleotides)
    @strand = nucleotides
  end

  def to_rna
    transcribe(@strand)
  end

  private

  def transcribe(strand)
    strand.tr("T", "U")
  end
end
