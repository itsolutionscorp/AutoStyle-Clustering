class DNA

  def initialize(nucleotides)
    @nucleotides = nucleotides
    check_dna_sequence(@nucleotides)
  end

  def nucleotide_counts
    counts = DNA_BASES.map {|base| [base, count(base)] }
    Hash[counts]
  end

  def count(nucleotide)
    check_nucleotide(nucleotide)
    @nucleotides.count(nucleotide)
  end

  def valid_base?(base)
    BASES.include?(base)
  end

  def valid_dna_sequence?(sequence)
    sequence.chars.all? {|base| DNA_BASES.include?(base) }
  end

private

  def check_nucleotide(nucleotide)
    raise ArgumentError unless valid_base?(nucleotide)
  end

  def check_dna_sequence(sequence)
    raise ArgumentError unless valid_dna_sequence?(sequence)
  end

  DNA_BASES = %w|A C G T|
  BASES = DNA_BASES + ['U']

end
