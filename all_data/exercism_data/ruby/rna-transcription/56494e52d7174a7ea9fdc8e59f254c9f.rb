require 'forwardable'

class NucleicAcid < Struct.new(:sequence)
  extend Forwardable
  def_delegators :sequence, :==, :to_str
end

class RibonucleicAcid < NucleicAcid
end

class DeoxyribonucleicAcid < NucleicAcid
  THYMIDINE = ?T
  URACIL = ?U

  def to_rna
    RibonucleicAcid.new transcribed_sequence
  end

  private
  def transcribed_sequence
    sequence.tr(THYMIDINE, URACIL)
  end
end
