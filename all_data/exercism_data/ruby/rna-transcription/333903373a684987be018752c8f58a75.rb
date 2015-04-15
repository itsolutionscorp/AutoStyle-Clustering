class Nucleotide
  def self.inherited(klass)
    @subclasses ||= []
    @subclasses << klass
  end

  def self.subclasses
    @subclasses.dup
  end

  def initialize
    raise ArgumentError.new 'Nucleotide cannot be instantiated directly. Use ' \
      "a sub-class: #{self.class.subclasses.inspect}"
  end

  # Setup Behavior to pretend to be a string
  def ==(sequence)
    self.sequence == sequence.to_str.upcase
  end

  def inspect
    sequence.inspect
  end

  def to_s
    sequence.dup
  end

  def to_str
    sequence.dup
  end

  protected
  attr_reader :sequence
end

class RibonucleicAcid < Nucleotide
  def initialize(sequence)
    @sequence = sequence.to_s.upcase
    raise ArgumentError.new 'Invalid Sequence' unless /\A[ACGU]+\z/ =~ @sequence
  end
end

class DeoxyribonucleicAcid < Nucleotide
  def initialize(sequence)
    @sequence = sequence.to_s.upcase
    raise ArgumentError.new 'Invalid Sequence' unless /\A[ACGT]+\z/ =~ @sequence
  end

  def to_rna
    RibonucleicAcid.new(sequence.gsub('T', 'U'))
  end
end
