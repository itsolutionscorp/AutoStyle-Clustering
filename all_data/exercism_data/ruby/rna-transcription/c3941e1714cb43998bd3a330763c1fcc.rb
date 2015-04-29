class Complement
  def self.of_rna(string)
    NucleotideStrand.new(string).to_dna.string
  end

  def self.of_dna(string)
    NucleotideStrand.new(string).to_rna.string
  end
end

class NucleotideStrand
  attr_accessor :string

  def initialize(string)
    @string = string
  end

  def to_rna
    self.class.new(complement_of(:dna))
  end

  def to_dna
    self.class.new(complement_of(:rna))
  end

private
  DNA_COMPLEMENTS = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  COMPLEMENT_TABLES = { dna: DNA_COMPLEMENTS, rna: DNA_COMPLEMENTS.invert }

  def complement_of(from)
    string.chars.map {|symbol| COMPLEMENT_TABLES[from][symbol]}.join
  end
end
