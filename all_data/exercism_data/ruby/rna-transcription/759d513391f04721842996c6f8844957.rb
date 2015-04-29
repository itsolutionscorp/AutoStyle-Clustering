#!/usr/bin/env ruby

# Exercism 3
# RNA Transcription
# This program computes the RNA complete to a DNA nucleotide strand

class Complement
    @dna_seq = { 'C' => 'G',
                 'G' => 'C',
                 'T' => 'A',
                 'A' => 'U' }

  def self.of_dna(seq)

    seq.gsub(/[CGTA]/, @dna_seq)

  end

  def self.of_rna(seq)

    seq.gsub(/[GCAU]/, @dna_seq.invert)

  end

end
