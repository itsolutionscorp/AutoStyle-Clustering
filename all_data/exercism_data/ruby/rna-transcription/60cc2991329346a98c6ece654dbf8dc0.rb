module Complement
  def self.of_dna(dna)
    Dna.new(dna).compliment
  end

  def self.of_rna(rna)
    Rna.new(rna).compliment
  end

=begin
  # This is stupid, right?
  [:rna, :dna].each do |type|
    define_singleton_method("of_#{type}") do |string|
      Object.const_get(type.to_s.capitalize).new(string).compliment
    end
  end
=end
end

class ProteinString
  def initialize(string)
    @string = string
  end

  def compliment
    nucleotide_klass = self.class.const_get("#{self.class}Nucleotide")
    @string.chars.map { |c| nucleotide_klass.new(c).compliment }.join
  end

  class Nucleotide
    def initialize(char)
      @char = char
      raise ArgumentError unless valid_char?
    end

    def compliment 
      compliments[@char]
    end

    private
    def valid_char?
      compliments.has_key?(@char)
    end
  end

  class RnaNucleotide < Nucleotide
    private
    def compliments
      Rna::COMPLEMENTS
    end
  end

  class DnaNucleotide < Nucleotide
    private
    def compliments
      Dna::COMPLEMENTS
    end
  end
end

class Dna < ProteinString
  COMPLEMENTS = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }
end

class Rna < ProteinString
  COMPLEMENTS = Dna::COMPLEMENTS.invert 
end
