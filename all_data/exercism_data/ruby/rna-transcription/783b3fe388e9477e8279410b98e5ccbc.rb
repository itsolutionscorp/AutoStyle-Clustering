class Nucleotide

  NUCLEOTIDES = {
    adenine: "A",
    cytosine: "C",
    guanine: "G",
    thymine: "T",
    uracil: "U"
  }

  class << self
    NUCLEOTIDES.each do |key, value|
      define_method(key) do
        value
      end
    end
  end

end

class DNA

  def initialize(sequence)
    @sequence = sequence.chars
  end

  attr_reader :sequence

  def to_rna
    convert_sequence_to_rna.join
  end

  def convert_sequence_to_rna
    sequence.map { |character| character.gsub(Nucleotide.thymine, Nucleotide.uracil) }
  end

end
