module Genetics

  module Complements

    NUCLEOTIDE_COMPLEMENTS = {
      DNA: %q[GCTA],
      RNA: %q[CGAU]
    }

    def self.complement(to, strand)
      from = strand.class.name.split('::').last.to_sym
      strand.sequence.tr(NUCLEOTIDE_COMPLEMENTS[from], NUCLEOTIDE_COMPLEMENTS[to])
    end

  end

  module Strand

    def sequence
      @sequence
    end

  end

  class DNA

    include Strand

    def initialize(sequence)
      raise ArgumentError, "U is not a DNA nucleotide" if sequence =~ /U/

      @sequence = sequence
    end

  end

  class RNA

    include Strand

    def initialize(sequence)
      raise ArgumentError, "T is not a RNA nucleotide" if sequence =~ /T/

      @sequence = sequence
    end

  end

end

class Complement

  def self.of_dna(sequence)
    dna = Genetics::DNA.new(sequence)
    rna = Genetics::RNA.new(Genetics::Complements.complement(:RNA, dna))
    rna.sequence
  end

  def self.of_rna(sequence)
    rna = Genetics::RNA.new(sequence)
    dna = Genetics::DNA.new(Genetics::Complements.complement(:DNA, rna))
    dna.sequence
  end

end
