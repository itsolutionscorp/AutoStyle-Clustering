#!/usr/bin/env ruby

# Exercism 3
# RNA Transcription
# This program computes the RNA complete to a DNA nucleotide strand

class Complement
    @dna_seq = { 'C' => 'G',
                 'G' => 'C',
                 'T' => 'A',
                 'A' => 'U' }

    @rna_seq = @dna_seq.invert

  def self.of_dna(seq)

    seq.chars.map { |x| @dna_seq[x] }.join

  end

  def self.of_rna(seq)

    seq.chars.map { |x| @rna_seq[x] }.join

  end

end
