class NucleicAcid
  NUCLEOTIDES = ['A', 'T', 'C', 'G', 'U']

  def initialize(sequence)
    @sequence = sequence
    raise ArgumentError unless valid_sequence?
  end

  def count(type)
    raise ArgumentError unless valid_type? type
    @sequence.scan(type).count
  end

  def nucleotide_counts
    Hash[self.class::VALID_NUCLEOTIDES.map { |n| [n, count(n)] }]
  end

  private

  def valid_type?(type)
    NUCLEOTIDES.include? type
  end

  def valid_sequence?
    (@sequence.split('') - self.class::VALID_NUCLEOTIDES).size == 0
  end
end

class DNA < NucleicAcid
  VALID_NUCLEOTIDES = ['A', 'T', 'C', 'G']
end

class RNA < NucleicAcid
  VALID_NUCLEOTIDES = ['A', 'U', 'C', 'G']
end
