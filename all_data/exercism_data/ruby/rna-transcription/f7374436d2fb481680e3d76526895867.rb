class DNA
  ADENOSINE = 'A'
  CYTIDINE  = 'C'
  GUANOSINE = 'G'
  THYMIDINE = 'T'
  URACIL    = 'U'
  
  attr_reader :sequence
  
  def initialize(sequence)
    @sequence = sequence
  end
  
  def to_rna
    transcribe sequence, replace: THYMIDINE, with: URACIL
  end
  
  private
  
  def transcribe(str, replace: '', with: '')
    str.gsub replace, with
  end
end
