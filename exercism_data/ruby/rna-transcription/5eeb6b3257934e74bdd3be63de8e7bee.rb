module Nucleotides  
  def adenosine; 'A' end
  def cytidine;  'C' end
  def guanosine; 'G' end
  def thymidine; 'T' end
  def uracil;    'U' end
end

class DNA
  include Nucleotides
  
  attr_reader :sequence
  
  def initialize(sequence)
    @sequence = sequence
  end
  
  def to_rna
    sequence.tr(thymidine, uracil)
  end
end
