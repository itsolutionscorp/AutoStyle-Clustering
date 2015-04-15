class Nucleotide

  NUCLEOTIDES = {
    adenine: "A",
    cytosine: "C",
    guanine: "G",
    thymine: "T",
    uracil: "U"
  }

  def initialize(abbreviation)
    @abbreviation = abbreviation
  end

  def to_s
    @abbreviation
  end

  # some meta programming just for fun
  NUCLEOTIDES.each do |key, value|
    define_method("#{key}?") do
      @abbreviation == value
    end
  end

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
    @sequence = sequence.split('').map { |item| Nucleotide.new(item) }
  end

  attr_reader :sequence

  def to_rna
    sequence.map do |nucleotide|
      nucleotide.thymine? ? Nucleotide.uracil : nucleotide
    end.join
  end

end
