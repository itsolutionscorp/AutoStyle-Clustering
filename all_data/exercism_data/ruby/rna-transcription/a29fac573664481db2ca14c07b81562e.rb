class Nucleotide < Struct.new(:name, :abbreviation)
  def to_s
    abbreviation
  end

  alias_method :to_str, :to_s
end

class DNA
  NUCLEOTIDES = {
    adenine: Nucleotide.new("Adenine", "A"),
    cytosine: Nucleotide.new("Cytosine", "C"),
    guanine: Nucleotide.new("Guanine", "G"),
    thymine: Nucleotide.new("Thymine", "T"),
    uracil: Nucleotide.new("Uracil", "U"),
  }

  def initialize(coding_strand)
    @coding_strand = coding_strand
  end

  def to_rna
    transcribe_to_rna
  end

  private
    def transcribe_to_rna
      @coding_strand.gsub(NUCLEOTIDES[:thymine], 
                          NUCLEOTIDES[:uracil])
    end
end
