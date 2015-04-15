module Genetics

  class Sequence

    @@dna_nucleotides = %w[G C T A]
    @@rna_nucleotides = %w[C G A U]

    def initialize(sequence)
      @sequence = sequence.chars
    end

    def to_s
      @sequence.join
    end

  end

  class DNA < Sequence

    def initialize(sequence)
      sequence.chars.each do |nucleotide|
        raise ArgumentError, "#{nucleotide} nucleotide not in DNA" if @@dna_nucleotides.index(nucleotide).nil?
      end
      @sequence = sequence.chars
    end

    def to_rna
      rna_sequence = @sequence.map do |nucleotide|
        @@rna_nucleotides[@@dna_nucleotides.index(nucleotide)]
      end

      Genetics::RNA.new(rna_sequence.join)
    end

  end

  class RNA < Sequence

    def initialize(sequence)
      sequence.chars.each do |nucleotide|
        raise ArgumentError, "#{nucleotide} nucleotide not in RNA" if @@rna_nucleotides.index(nucleotide).nil?
      end
      @sequence = sequence.chars
    end

    def to_dna
      dna_sequence = @sequence.map do |nucleotide|
        @@dna_nucleotides[@@rna_nucleotides.index(nucleotide)]
      end

      Genetics::DNA.new(dna_sequence.join)
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
