class DNA
  
  URACIL    = 'U'
  THYMIDINE = 'T'
  
  attr_accessor :dna_string
  
  def initialize(dna_string)
    @dna_string = dna_string
  end
  
  def to_rna
    translate_string
  end
  
  private
  
  def translate_string
    dna_string.gsub(THYMIDINE, URACIL)
  end
end
