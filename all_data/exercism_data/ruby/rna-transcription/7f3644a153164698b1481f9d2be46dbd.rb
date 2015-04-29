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
  NUCLEOTIDE_COMPLEMENTS = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U',
  }

  def self.from_string(string)
    Dna.new(string.chars)
  end

  def to_rna
    Rna.new(nucleotides.map(&NUCLEOTIDE_COMPLEMENTS.method(:[])))
  end
end

class Rna < Polynucleotide
  NUCLEOTIDE_COMPLEMENTS = {
    'C' => 'G',
    'G' => 'C',
    'A' => 'T',
    'U' => 'A',
  }

  def self.from_string(string)
    Rna.new(string.chars)
  end

  def to_dna
    Dna.new(nucleotides.map { |nucleotide| NUCLEOTIDE_COMPLEMENTS[nucleotide] })
  end
end
