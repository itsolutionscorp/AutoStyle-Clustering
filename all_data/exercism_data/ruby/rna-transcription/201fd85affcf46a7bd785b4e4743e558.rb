class DNA
  attr_reader :dna_sequence
  
  @@thymidine='T'
  @@uracil='U'
  
  def initialize(sequence)
    @dna_sequence = sequence
  end

  def to_rna
    replace_thymidine(dna_sequence)
  end

  private 
    def replace_thymidine(dna_sequence)
      dna_sequence.tr(@@thymidine, @@uracil)
    end
end
