module Complement
  def self.of_dna(dna)
    Dna.new(dna).compliment
  end

  def self.of_rna(rna)
    Rna.new(rna).compliment
  end
end

class ProteinString
  def initialize(string)
    @string = string
  end

  def compliment
    compliment_map.join('')
  end

  private
  def compliment_map
    @string.chars.map { |c| Nucleotide.new(c, self.class).compliment }
  end

  class Nucleotide
    def initialize(char, type)
      @char, @type = char, type
      raise ArgumentError unless valid_type? && valid_char?
    end

    def compliment 
      compliments[@char]
    end

    private
    def compliments
      @type.const_get(:COMPLEMENTS)
    end

    def valid_type?
      @type.ancestors.include?(ProteinString)
    end

    def valid_char?
      compliments.has_key?(@char)
    end
  end
end

class Dna < ProteinString
  COMPLEMENTS = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' } 
end

class Rna < ProteinString
  COMPLEMENTS = Dna::COMPLEMENTS.invert 
end
