class Strand
  def initialize(nucleotides)
    @nucleotides = nucleotides
  end

  attr_reader :nucleotides

  def transcribe
    @nucleotides.gsub(/T/, "U")
  end
end

class RNA
  def initialize(nucleotides)
    @strand = Strand.new(nucleotides)
  end

  def strand
    @strand.nucleotides
  end
end

class DNA < RNA
  def to_rna
    rna = RNA.new(@strand.transcribe)
    rna.strand
  end
end
