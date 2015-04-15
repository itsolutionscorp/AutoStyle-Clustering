class DNA

  def initialize(strand)
    @strand = strand
  end

  def to_rna
    @strand.tr( dna_to_rna_transcriber.dna_char, dna_to_rna_transcriber.rna_char)
  end

  private

  def dna_to_rna_transcriber
    transcriber = Struct.new(:dna_char, :rna_char)
    transcriber.new("T","U")
  end

end
