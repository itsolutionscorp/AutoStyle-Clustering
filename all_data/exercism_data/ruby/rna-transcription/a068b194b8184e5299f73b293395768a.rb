class NucleicAcid
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

  def each_nucleotide(&block)
    sequence.each_char(&block)
  end

  # Setup behavior to mimic a string
  def ==(sequence)
    self.sequence == sequence.to_str.upcase
  end

  def inspect
    sequence.inspect
  end

  def to_str
    sequence.dup
  end
  alias_method :to_s, :to_str

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
    rna_compliment = { ?A => ?A, ?C => ?C, ?G => ?G, ?T => ?U }
    RibonucleicAcid.new(dna.each_nucleotide.map{ |n| rna_compliment[n] }.join)
  end
end
