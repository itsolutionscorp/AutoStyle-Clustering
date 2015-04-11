require "English"

class Complement
  DNA_NUCLEOTIDES = 'GCTA'
  RNA_NUCLEOTIDES = 'CGAU'

  def self.of_dna(strand)
    transcribe('DNA', strand, DNA_NUCLEOTIDES, RNA_NUCLEOTIDES)
  end

  def self.of_rna(strand)
    transcribe('RNA', strand, RNA_NUCLEOTIDES, DNA_NUCLEOTIDES)
  end

  private

  def self.transcribe(type, strand, from, to)
    if strand =~ /[^#{from}]/
      raise ArgumentError, "#{$MATCH} is not a #{type} nucleotide"
    end
    strand.tr(from, to)
  end
end
