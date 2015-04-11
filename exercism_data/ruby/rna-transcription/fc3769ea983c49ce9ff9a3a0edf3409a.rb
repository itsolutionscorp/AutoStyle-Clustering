#!/usr/bin/env ruby -w

class Complement
  def Complement.of_dna(strand)
    DNAComplementor.new.run strand
  end

  def Complement.of_rna(strand)
    RNAComplementor.new.run strand
  end
end

#----------------------------------------------------------------------------
class Complementor
  def run(strand, acc='')
    return acc if strand.length.zero?
    run strand[TAIL], acc + complement(strand.chars.first)
  end

  private
    TAIL = 1..-1

    def complement(nucleotide)
      to[from.index(nucleotide)]
    end
end

#----------------------------------------------------------------------------
class DNAComplementor < Complementor
  def from; %w(G C T A); end
  def to;   %w(C G A U); end
end

#----------------------------------------------------------------------------
class RNAComplementor < Complementor
  def from; %w(G C U A); end
  def to;   %w(C G A T); end
end
