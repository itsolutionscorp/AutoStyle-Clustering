require 'pry'

class Hamming

  def self.compute(string1, string2)
    truncate_string1 = string1[0 ... string2.length]
    strand1 = Strand.new(truncate_string1)
    strand2 = Strand.new(string2)

    strand1.zip(strand2).select { |nucleotide1, nucleotide2| nucleotide1 != nucleotide2 }.count
  end
end

class Strand
  include Enumerable

  def initialize(string)
    @strand = string.chars.map { |char| Nucleotide.new(char) }
  end

  def each
    @strand.each { |x| yield(x) }
  end
end

class Nucleotide
  attr_reader :char

  def initialize(char)
    @char = char
  end

  def ==(nucleotide)
    @char == nucleotide.char
  end
end
