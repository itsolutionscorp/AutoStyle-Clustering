#!/usr/bin/env ruby

# Exercism 32
# Nucleotide Count

class Nucleotide

  def self.from_dna(string)
    raise ArgumentError if string =~ /[^ACTG]/
    @string = string
  end

  def count(letter)
    @string.count(letter)
  end

end


class String

  def histogram
    { 'A' => self.downcase.count('a'),
      'T' => self.downcase.count('t'),
      'C' => self.downcase.count('c'),
      'G' => self.downcase.count('g') }
  end

end
