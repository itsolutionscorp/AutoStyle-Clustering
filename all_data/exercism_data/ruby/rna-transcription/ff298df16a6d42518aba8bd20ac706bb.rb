class Strand
  class << self; attr_accessor :mapping; end

  def initialize(sequence)
    @sequence = sequence
  end

  def complement
    @sequence.tr mapping.keys.join, mapping.values.join
  end

  def mapping
    self.class.mapping
  end
end

class DNA < Strand
  self.mapping = { 'G' => 'C', 'C' => 'G', 'A' => 'U', 'T' => 'A' }
end

class RNA < Strand
  self.mapping = DNA.mapping.invert
end

class Complement
  STRAND_TYPES = { dna: DNA, rna: RNA }

  STRAND_TYPES.each do |name, klazz|
    define_singleton_method("of_#{name}") do |sequence|
      klazz.new(sequence).complement
    end
  end
end
