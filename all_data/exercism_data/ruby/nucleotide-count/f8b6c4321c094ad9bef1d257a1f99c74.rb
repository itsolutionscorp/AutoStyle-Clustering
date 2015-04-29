require 'pry'

class Nucleotide
  attr_reader :dna_string

  def initialize(dna_string)
    raise ArgumentError if dna_string.gsub(/[ATCG]/, '').length > 0
    @dna_string = dna_string
  end

  def self.from_dna(dna_string)
    new(dna_string)
  end

  def count(character)
    dna_string.scan(/#{character}/).size
  end

  def histogram
    { 'A' => count('A'), 'T' => count('T'), 'C' => count('C' ), 'G' => count('G') }
  end
end
