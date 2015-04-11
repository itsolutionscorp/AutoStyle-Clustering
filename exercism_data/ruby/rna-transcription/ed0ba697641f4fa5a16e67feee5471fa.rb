#!/usr/bin/env ruby

class Complement
  @dna, @rna = 'CGTA', 'GCAU'

  def self.of_rna(str)
    str.tr @rna, @dna
  end

  def self.of_dna(str)
    str.tr @dna, @rna
  end
end
