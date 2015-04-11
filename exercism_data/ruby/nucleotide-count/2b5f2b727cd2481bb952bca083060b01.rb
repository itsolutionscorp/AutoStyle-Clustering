VALID_NUCLEOTIDES = { dna: %w(A C G T), rna: %w(A C G U) }

class DNA
  def initialize(strand)
    @strand = strand
    @nucleotide_counts = Hash.new(0)
    VALID_NUCLEOTIDES[:dna].each { |nucleotide| @nucleotide_counts[nucleotide] = 0 }
    @strand.each_char do |nucleotide|
      @nucleotide_counts[nucleotide] += 1 if valid_nucleotide?(nucleotide, :dna)
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

  def valid_nucleotide?(nucleotide, type=nil)
    valid_nucleotides = type ? VALID_NUCLEOTIDES[type.to_sym] : VALID_NUCLEOTIDES.values.flatten.uniq
    if valid_nucleotides.include?(nucleotide)
      true
    else
      raise ArgumentError, "A #{type ? type.to_s+' ' : ''}nucleotide must be one of #{valid_nucleotides.join(', ')}."
    end
  end
end
