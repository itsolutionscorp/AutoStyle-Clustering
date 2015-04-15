module Complement
  def self.of_dna(strand)
    Dna.from_string(strand).to_rna.to_s
  end

  def self.of_rna(strand)
    Rna.from_string(strand).to_dna.to_s
  end
end

class Nucleotide
  def initialize(type)
    @type = type
  end

  def self.from_char(char)
    MAPPING[char.to_sym] or raise "No nucleotide for '#{char}' found"
  end

  def to_s
    @type
  end

  ADENINE = Nucleotide.new('A')
  CYTOSINE = Nucleotide.new('C')
  GUANINE = Nucleotide.new('G')
  THYMIDINE = Nucleotide.new('T')
  URACIL = Nucleotide.new('U')

  MAPPING = {
    A: ADENINE,
    C: CYTOSINE,
    G: GUANINE,
    T: THYMIDINE,
    U: URACIL,
  }
end

class Polynucleotide
  attr_reader :nucleotides

  def initialize(nucleotides)
    @nucleotides = nucleotides
  end

  def to_s
    nucleotides.join
  end

  def self.from_string(string)
    self.new(string.chars.map(&Nucleotide.method(:from_char)))
  end

  private
  def complement(klass, mapping)
    klass.new(mapping.values_at(*nucleotides))
  end
end

class Dna < Polynucleotide
  TO_RNA = {
    Nucleotide::GUANINE => Nucleotide::CYTOSINE,
    Nucleotide::CYTOSINE => Nucleotide::GUANINE,
    Nucleotide::THYMIDINE => Nucleotide::ADENINE,
    Nucleotide::ADENINE => Nucleotide::URACIL,
  }

  def to_rna
    complement(Rna, TO_RNA)
  end
end

class Rna < Polynucleotide
  TO_DNA = Dna::TO_RNA.invert

  def to_dna
    complement(Dna, TO_DNA)
  end
end
