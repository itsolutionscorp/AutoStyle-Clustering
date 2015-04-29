#!/usr/bin/ruby

class DNA
  def initialize(strand)
    if strand =~ /[^A|^T|^C|^G]/
      raise ArgumentError
    end
    @strand = strand
  end

  def count(nucleotide)
    if !(['A','T','G','C','U'].include? nucleotide)
      raise ArgumentError
    end
    nucleotides.count(nucleotide)
  end

  def nucleotide_counts
    {'A' => nucleotides.count('A'), 
     'T' => nucleotides.count('T'), 
     'C' => nucleotides.count('C'), 
     'G' => nucleotides.count('G')}
  end

  def nucleotides
    @nucleotides ||= @strand.scan(/./)
  end
end
