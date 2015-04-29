#!/usr/bin/env ruby

# Exercism 3
# RNA Transcription
# This program computes the RNA complete to a DNA nucleotide strand

class Complement

  def self.of_dna(seq)

    seq.tr('CGTA', 'GCAU')

  end

  def self.of_rna(seq)

    seq.tr('GCAU', 'CGTA')

  end

end
