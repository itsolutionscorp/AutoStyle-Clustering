require 'forwardable'

class NucleicAcid
  attr_reader :text
  def initialize(text)
    @text = text
  end

  extend Forwardable
  def_delegators :@text, :to_s, :to_str, :==
end

class RibonucleicAcid < NucleicAcid
end

class DeoxyribonucleicAcid < NucleicAcid
  def to_rna
    RibonucleicAcid.new(text.gsub('T','U'))
  end
end
