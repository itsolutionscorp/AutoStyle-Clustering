class DNA
  
  THYMIDINE = 'T'
  URACIL = 'U'

  attr_reader :sequence
  
  def initialize(sequence)
    @sequence = sequence
  end

  def to_rna
    replace_thymidine
  end

  private 
    def replace_thymidine
      sequence.tr(DNA::THYMIDINE, DNA::URACIL)
    end
end
