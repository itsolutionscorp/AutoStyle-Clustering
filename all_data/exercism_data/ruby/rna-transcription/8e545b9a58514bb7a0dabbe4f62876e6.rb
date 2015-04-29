class Strand
  class << self
    attr_accessor :mapping
  end

  attr_accessor :sequence

  def initialize(sequence)
    self.sequence = sequence
  end

  def complement
    sequence.chars.map { |n| self.class.mapping[n] }.join
  end
end

class DNA < Strand
  self.mapping = { 'G' => 'C', 'C' => 'G', 'A' => 'U', 'T' => 'A' }
end

class RNA < Strand
  self.mapping = { 'G' => 'C', 'C' => 'G', 'A' => 'T', 'U' => 'A' }
end

class Complement
  def self.of_dna(strand)
    DNA.new(strand).complement
  end

  def self.of_rna(strand)
    RNA.new(strand).complement
  end
end
