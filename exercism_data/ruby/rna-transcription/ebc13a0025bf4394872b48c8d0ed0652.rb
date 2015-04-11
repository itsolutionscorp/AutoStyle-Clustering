require 'forwardable'

class Acid
  extend Forwardable
  def_delegators :strand, :to_str, :to_s, :==
  attr_reader :strand

  def initialize(strand)
    @strand = strand
  end

end

class RibonucleicAcid < Acid
end

class DeoxyribonucleicAcid < Acid

  def to_rna
    RibonucleicAcid.new(strand.gsub("T", "U"))
  end

end
