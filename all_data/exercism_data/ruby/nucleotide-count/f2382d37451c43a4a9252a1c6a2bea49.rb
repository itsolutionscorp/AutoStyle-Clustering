class DNA
  DNA_NUCLEOTIDES = %w(A T C G)
  NUCLEOTIDES = DNA_NUCLEOTIDES + ['U']

  def initialize dna
    raise ArgumentError unless valid_dna? dna

    @counts = Hash[NUCLEOTIDES.collect { |n| [n, dna.count(n)] }]
  end

  def count nucleotide
    raise ArgumentError unless nucleotide? nucleotide

    @counts[nucleotide]
  end

  def nucleotide_counts
    select_dna_nucleotides
  end

  private

  def select_dna_nucleotides
    @counts.select { |k| DNA_NUCLEOTIDES.include? k }
  end

  def valid_dna? dna
    (dna.chars.to_a - DNA_NUCLEOTIDES).empty?
  end

  def nucleotide? nucleotide
    NUCLEOTIDES.include? nucleotide
  end
end
