require 'forwardable'

class NucleicAcid
  extend Forwardable

  def initialize(sequence)
    @sequence = sequence.to_s.upcase
    raise ArgumentError.new 'Invalid Sequence' unless made_of? allowed_nucleotides
  end

  def made_of?(nucleotides)
    sequence =~ /\A[#{nucleotides.join.upcase}]+\z/
  end

  def allowed_nucleotides
    %w( A C G T U )
  end

  def_delegators :@sequence, :==, :inspect, :to_str, :to_s
  def_delegator  :@sequence, :each_char, :each_nucleotide
  def_delegator  :@sequence, :tr, :transcribe

  protected
  attr_reader :sequence
end

class RibonucleicAcid < NucleicAcid
  def allowed_nucleotides
    %w( A C G U )
  end
end

class DeoxyribonucleicAcid < NucleicAcid
  def allowed_nucleotides
    %w( A C G T )
  end

  def to_rna
    RibonucleicAcidPolymerase.new.transcribe(self)
  end
end

class RibonucleicAcidPolymerase
  def transcribe(dna)
    dna_nucleotides = 'ACGT'
    rna_compliment = 'ACGU'
    RibonucleicAcid.new(dna.transcribe dna_nucleotides, rna_compliment)
  end
end
