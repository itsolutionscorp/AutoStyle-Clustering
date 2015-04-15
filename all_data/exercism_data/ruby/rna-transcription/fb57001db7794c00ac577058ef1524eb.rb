module Complement
  def self.of_dna(strand)
    Dna.from_string(strand).to_rna.to_s
  end

  def self.of_rna(strand)
    Rna.from_string(strand).to_dna.to_s
  end
end

class Polynucleotide
  attr_reader :nucleotides

  def initialize(nucleotides)
    @nucleotides = nucleotides
  end

  def to_s
    nucleotides.join
  end
end

class Dna < Polynucleotide
  TO_RNA = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U',
  }

  def self.from_string(string)
    Dna.new(string.chars)
  end

  def to_rna
    Rna.new(TO_RNA.values_at(*nucleotides))
  end
end

class Rna < Polynucleotide
  TO_DNA = Dna::TO_RNA.invert

  def self.from_string(string)
    Rna.new(string.chars)
  end

  def to_dna
    Dna.new(TO_DNA.values_at(*nucleotides))
  end
end
