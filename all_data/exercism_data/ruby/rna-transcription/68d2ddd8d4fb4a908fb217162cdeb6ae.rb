class DNA
  def initialize(nucleotides)
    @dna_nucleotides = nucleotides
    @rna_nucleotides = ""
  end
  
  attr_reader   :dna_nucleotides
  attr_accessor :rna_nucleotides
  
  def to_rna
    dna_nucleotides.each_char { |n| translate(n) }
    rna_nucleotides
  end
  
  def translate(nucleotide)
    if nucleotide.eql?("T")
      rna_nucleotides << "U"
    else
      rna_nucleotides << nucleotide
    end
  end
end
