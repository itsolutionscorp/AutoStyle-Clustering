module Genetics

  class Sequence

    @@dna_nucleotides = %q[GCTA]
    @@rna_nucleotides = %q[CGAU]

    def to_s
      @sequence
    end

  end

  class DNA < Sequence

    def initialize(sequence)
      sequence.chars.each do |nucleotide|
        raise ArgumentError, "#{nucleotide} nucleotide not in DNA" unless @@dna_nucleotides.include?(nucleotide)
      end
      @sequence = sequence
    end

    def to_rna
      rna_sequence = @sequence.tr(@@dna_nucleotides, @@rna_nucleotides)

      Genetics::RNA.new(rna_sequence)
    end

  end

  class RNA < Sequence

    def initialize(sequence)
      sequence.chars.each do |nucleotide|
        raise ArgumentError, "#{nucleotide} nucleotide not in RNA" unless @@rna_nucleotides.include?(nucleotide)
      end
      @sequence = sequence
    end

    def to_dna
      dna_sequence = @sequence.tr(@@rna_nucleotides, @@dna_nucleotides)

      Genetics::DNA.new(dna_sequence)
    end

  end

end

class Complement

  def self.of_dna(sequence)
    Genetics::DNA.new(sequence).to_rna.to_s
  end

  def self.of_rna(sequence)
    Genetics::RNA.new(sequence).to_dna.to_s
  end

end
