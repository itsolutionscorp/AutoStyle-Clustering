module Genetics
  module Sequence
    attr_reader :sequence

    def initialize(sequence)
      @sequence = sequence
    end

    def to_dna
      dna = sequence.tr(self.class.nucleotides, DNA.nucleotides)
      Genetics::RNA.new(dna)
    end

    def to_rna
      rna = sequence.tr(self.class.nucleotides, RNA.nucleotides)
      Genetics::RNA.new(rna)
    end
  end

  class DNA
    include Sequence

    def self::nucleotides
      @nucleotides ||= 'GCTA'
    end
  end

  class RNA
    include Sequence

    def self.nucleotides
      @nucleotides ||= 'CGAU'
    end
  end
end

module Complement
  def self.of_dna(sequence)
    Genetics::DNA.new(sequence).to_rna.sequence
  end

  def self.of_rna(sequence)
    Genetics::RNA.new(sequence).to_dna.sequence
  end
end
