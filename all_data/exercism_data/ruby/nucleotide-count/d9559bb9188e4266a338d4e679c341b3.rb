VALID_NUCLEOTIDES = %w(A C G T U)
VALID_DNA_NUCLEOTIDES = %w(A C G T)

class DNA
  def initialize(strand)
    @strand = strand
    @nucleotide_counts = Hash.new(0)
    VALID_DNA_NUCLEOTIDES.each { |nucleotide| @nucleotide_counts[nucleotide] = 0 }
    @strand.each_char do |nucleotide|
      @nucleotide_counts[nucleotide] += 1 if valid_dna_nucleotide?(nucleotide)
    end
  end

  def count(nucleotide)
    valid_nucleotide?(nucleotide)
    @nucleotide_counts[nucleotide]
  end
  
  def nucleotide_counts
    @nucleotide_counts
  end

private

  def valid_dna_nucleotide?(nucleotide)
    if VALID_DNA_NUCLEOTIDES.include?(nucleotide)
      true
    else
      raise ArgumentError, "A DNA nucleotide must be one of #{VALID_DNA_NUCLEOTIDES.join(', ')}."
    end
  end

  def valid_nucleotide?(nucleotide)
    if VALID_NUCLEOTIDES.include?(nucleotide)
      true
    else
      raise ArgumentError, "A nucleotide must be one of #{VALID_NUCLEOTIDES.join(', ')}."
    end
  end
end
