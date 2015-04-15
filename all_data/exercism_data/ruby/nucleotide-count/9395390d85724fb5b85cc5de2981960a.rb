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
    @strand.count(nucleotide)
  end

  def nucleotide_counts
    {'A' => @strand.count('A'), 
     'T' => @strand.count('T'), 
     'C' => @strand.count('C'), 
     'G' => @strand.count('G')}
  end
end
