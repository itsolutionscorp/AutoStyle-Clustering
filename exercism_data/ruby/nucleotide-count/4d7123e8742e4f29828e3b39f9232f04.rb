module Nucleotide

  DNA_TYPES = %w(A T G C)
  RNA_TYPES = %w(A C G U)

  ALL_TYPES = (DNA_TYPES + RNA_TYPES).uniq

  def self.exists?(nucleotide)
    ALL_TYPES.include? nucleotide
  end

  def self.in_dna?(nucleotide)
    DNA_TYPES.include? nucleotide
  end

end

class DNA

  def initialize(strand)
    @nucleotides = strand.chars.each do |nucleotide|
      raise ArgumentError unless Nucleotide.in_dna? nucleotide
    end
  end

  def count(nucleotide)
    raise ArgumentError unless Nucleotide.exists? nucleotide
    @nucleotides.count(nucleotide)
  end

  def nucleotide_counts
    Nucleotide::DNA_TYPES.each_with_object(Hash.new(0)) do |symbol, counter|
      counter[symbol] = count(symbol)
    end
  end

end
