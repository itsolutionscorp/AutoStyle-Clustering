module Nucleotide
  def adenine;  'A'; end
  def cytosine; 'C'; end
  def guanine;  'G'; end
  def thymine;  'T'; end
  def uracil;   'U'; end
end

class DNA
  include Nucleotide
  
  attr_reader :sequence

  def initialize(sequence)
    @sequence = sequence
  end

  def to_rna
    sequence.gsub(thymine, uracil)
  end
end
