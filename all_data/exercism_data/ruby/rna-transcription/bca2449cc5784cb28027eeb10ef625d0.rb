require 'pry'
class Complement

  THYMIDINE = "T"
  URACIL    = "U"

  def self.of_dna sequence
    dna = BaseComplement.new(sequence)
    dna.compute_complement URACIL
  end
    
  def self.of_rna sequence
    rna = BaseComplement.new(sequence)
    rna.compute_complement THYMIDINE
  end
end

class BaseComplement

  BASE_SEQUENCE = {
    "C" => "G",
    "G" => "C",
    "U" => "A",
    "T" => "A",
  }

  ADENINE   = "A"

  attr_reader :sequence, :base_sequence

  def initialize sequence
    @sequence      = sequence
    @base_sequence = BASE_SEQUENCE
  end

  def add_conversion nucleotide
    base_sequence[ADENINE] = nucleotide
  end

  def complement_nucleotides
    sequence.chars.map! do |nucleotide|
      base_sequence[nucleotide]
    end
  end

  def compute_complement adenine_complement
    add_conversion adenine_complement
    complement_nucleotides.join
  end
end
    
     
