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

  def initialize(dna_string)
    @nucleotides = dna_string.to_s.chars.each do |nucleotide|
      raise ArgumentError unless Nucleotide.in_dna? nucleotide
    end
  end

  def count(nucleotide)
    raise ArgumentError unless Nucleotide.exists? nucleotide
    nucleotide_counts[nucleotide]
  end

  def nucleotide_counts
    @cached_count ||= Nucleotide::DNA_TYPES.each_with_object(Hash.new(0)) do |symbol, counter|
      counter[symbol] = @nucleotides.grep(symbol).count
    end
  end

end
