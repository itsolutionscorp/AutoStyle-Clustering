# Steffen Parratt (username Neffetski) 14 Nov 2014
# Exercise 3, v2 (after reviewing others' solutions)

module Complement
  
  DNA = 'GCTA'
  RNA = 'CGAU'

  # Given a DNA strand, return its RNA complement
  def self.of_dna (strand)
    strand.tr(DNA, RNA)
  end
  
  # Given a RNA strand, return its DNA complement
  def self.of_rna (strand)
    strand.tr(RNA, DNA)
  end

end
