module Complement
  extend self
  
  def of_dna(sequence)
    DNAComplementer.new(sequence).translate
  end

  def of_rna(sequence)
    RNAComplementer.new(sequence).translate
  end

  class DNAComplementer
    attr_reader :sequence

    def initialize(sequence)
      @sequence = sequence 
    end

    def translate
      sequence.chars.map { |n| complements[n] }.join
    end

    def complements
      @complements ||= { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }
    end
  end

  class RNAComplementer < DNAComplementer
    def complements
      @complements ||= super.invert
    end
  end
end
