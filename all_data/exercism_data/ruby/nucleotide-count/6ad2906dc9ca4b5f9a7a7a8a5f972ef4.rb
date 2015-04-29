class DNA
  VALID_NUCLEOTIDES =  %w{A T C G U}
  VALID_DNA_NUCLEOTIDES =  %w{A T C G}

  def initialize sequence
    @sequence = sequence
    validate_dna_sequence
  end

  def count nucleotide
    raise ArgumentError unless valid_nucleotide? nucleotide
    @sequence.count nucleotide
  end

  def nucleotide_counts
    result = {}
    VALID_DNA_NUCLEOTIDES.each { |nucleotide| result[nucleotide] = count(nucleotide) }
    result
  end

  private

  def validate_dna_sequence
    @sequence.chars.each { |nucleotide| raise ArgumentError unless valid_dna_nucleotide? nucleotide }
  end


  def valid_dna_nucleotide? nucleotide
    VALID_DNA_NUCLEOTIDES.include? nucleotide
  end

  def valid_nucleotide? nucleotide
    VALID_NUCLEOTIDES.include? nucleotide
  end


end
