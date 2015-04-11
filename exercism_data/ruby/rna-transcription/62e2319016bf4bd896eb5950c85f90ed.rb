class Strand
  def initialize(nucleotides)
    @nucleotides = nucleotides
  end

  attr_reader :nucleotides

  def transcribe
    @nucleotides.gsub(/T/, "U")
  end
end

class RNA < DNA
  def initialize(nucleotides)
    super(nucleotides)
    @strand.transcribe
  end
  
  def strand
    @strand.nucleotides
  end
end

class DNA 
  def initialize(nucleotides)
    @strand = Strand.new(nucleotides)
  end

  def to_rna
    rna = RNA.new(@strand.transcribe)
    rna.strand
  end
end
