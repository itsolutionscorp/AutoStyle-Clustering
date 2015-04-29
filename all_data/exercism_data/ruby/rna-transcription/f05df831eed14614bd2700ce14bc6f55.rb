class Complement
  attr_reader :dna_or_rna

  def initialize(dna_or_rna)
    @dna_or_rna = dna_or_rna
    raise ArgumentError, error_message unless dna_or_rna_valid?
  end

  def complement
    dna_or_rna.chars.inject("") do |complement, character|
      complement += complements[character]
    end
  end

  def self.of_dna(dna)
    DNAComplement.new(dna).complement
  end

  def self.of_rna(rna)
    RNAComplement.new(rna).complement
  end

protected

  def dna_to_rna
    {
      "G" => "C",
      "C" => "G",
      "T" => "A",
      "A" => "U"
    }
  end

  def dna_or_rna_valid?
    dna_or_rna.chars.all? do |char|
      complements[char]
    end
  end

  def error_message
    "That is not a valid #{type} sequence"
  end
end

class DNAComplement < Complement
protected

  def complements
    dna_to_rna
  end

  def type
    "DNA"
  end
end

class RNAComplement < Complement
protected

  def complements
    dna_to_rna.invert
  end

  def type
    "RNA"
  end
end
