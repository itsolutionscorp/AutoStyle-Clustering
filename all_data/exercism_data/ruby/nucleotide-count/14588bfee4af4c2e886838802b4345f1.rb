class NucleicAcid
  NUCLEOTIDES = %w[A T C G U]

  def initialize(sequence)
    @sequence = sequence
    raise ArgumentError unless valid_sequence?
  end

  def count(type)
    raise ArgumentError unless valid_type? type
    @sequence.scan(type).count
  end

  def nucleotide_counts
    Hash[valid_nucleotides.map { |n| [n, count(n)] }]
  end

  private

  def valid_type?(type)
    NUCLEOTIDES.include? type
  end

  def valid_sequence?
    (@sequence.split('') - valid_nucleotides).size == 0
  end

  def valid_nucleotides
    raise 'Implement in child'
  end
end

class DNA < NucleicAcid
  def valid_nucleotides
    %w[A T C G]
  end
end

class RNA < NucleicAcid
  def valid_nucleotides
    %w[A U C G]
  end
end
