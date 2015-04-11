class Complement
  def self.of_rna(string)
    RNAStrand.new(string).to_dna.string
  end

  def self.of_dna(string)
    DNAStrand.new(string).to_rna.string
  end
end

class NucleotideStrand
  attr_accessor :string

  def initialize(string)
    @string = string
  end

protected
  DNA_COMPLEMENTS = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }

  COMPLEMENT_TABLES = { dna: DNA_COMPLEMENTS, rna: DNA_COMPLEMENTS.invert }

  def complement_of(from)
    string.map_chars {|symbol| COMPLEMENT_TABLES[from][symbol]}
  end
end

class DNAStrand < NucleotideStrand
  def to_rna
    RNAStrand.new(complement_of(:dna))
  end
end

class RNAStrand < NucleotideStrand
  def to_dna
    DNAStrand.new(complement_of(:rna))
  end
end

class String
  def map_chars(&block)
    # TODO: Create Enumerator(?) if block not given (nil). But, how to force join?
    chars.map(&block).join
  end
end
