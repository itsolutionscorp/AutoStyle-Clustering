class DNA 
  def initialize(nucleotides)
    @strand = nucleotides
  end

  attr_reader :strand

  def to_rna
    rna = RNA.new(@strand)
    rna.strand
  end
end

class RNA < DNA
  def initialize(nucleotides)
    super(nucleotides)
    transcribe(@strand)
  end

  private

  def transcribe(strand)
    strand.gsub!(/T/, "U")
  end
end
