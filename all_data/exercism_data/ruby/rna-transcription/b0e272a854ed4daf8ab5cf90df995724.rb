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
    nucleotides
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
    Dna.new(string)
  end

  def to_rna
    Rna.new(nucleotides.tr(TO_RNA.keys.join, TO_RNA.values.join))
  end
end

class Rna < Polynucleotide
  TO_DNA = {
    'C' => 'G',
    'G' => 'C',
    'A' => 'T',
    'U' => 'A',
  }

  def self.from_string(string)
    Rna.new(string)
  end

  def to_dna
    Dna.new(nucleotides.tr(TO_DNA.keys.join, TO_DNA.values.join))
  end
end
