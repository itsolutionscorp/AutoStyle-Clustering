#!/usr/bin/env ruby

# Exercism 3
# RNA Transcription
# This program computes the RNA complete to a DNA nucleotide strand

# dna_to_rna
# `C` -> `G`
# `G` -> `C`
# `T` -> `A`
# `A` -> `U`

# rna_to_dna
# `G` -> `C`
# `C` -> `G`
# `A` -> `T`
# `U` -> `A`

class Complement

  def self.of_dna(seq)

    dna_seq = { 'C' => 'G', 'G' => 'C', 'T' => 'A', 'A' => 'U' }

    dna_comp = ''
    seq.split('').each { |x| dna_comp += dna_seq[x].to_s }
    dna_comp

  end

  def self.of_rna(seq)

    rna_seq = { 'G' => 'C', 'C' => 'G', 'A' => 'T', 'U' => 'A' }

    rna_comp = ''
    seq.split('').each { |x| rna_comp += rna_seq[x].to_s }
    rna_comp

  end

end
