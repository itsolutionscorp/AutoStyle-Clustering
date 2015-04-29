#!/usr/bin/env ruby

class Complement
  @rna_complement = Hash.new { |x, y| }
  @rna_complement['G'] = 'C'
  @rna_complement['C'] = 'G'
  @rna_complement['T'] = 'A'
  @rna_complement['A'] = 'U'

  def self.of_dna(dna_strand)
    @dna = dna_strand
    @rna_strand = ""

    0.upto(@dna.length - 1) do |n|
      nucleotide = @dna[n]
      @rna_strand << @rna_complement[nucleotide]
    end

    return @rna_strand
  end

  def self.of_rna(rna_strand)
    @rna = rna_strand
    @dna_strand = ""

    0.upto(@rna.length - 1) do |n|
      nucleotide = @rna[n]
      @dna_strand << @rna_complement.invert[nucleotide]
    end

    return @dna_strand
  end
end
