class DNA
  DNA_NUCLEOTIDES = %w(A C G T)
  RNA_NUCLEOTIDES = %w(A C G U)

  def initialize(sequence)
    @sequence = sequence.split(//)
  end

  def count(nucleotide)
    unless valid_nucleotides.include?(nucleotide)
      raise ArgumentError.new("#{nucleotide} is not a valid nucleotide (#{valid_nucleotides.join(', ')})")
    end
    @sequence.select{|n| n == nucleotide}.size
  end

  def nucleotide_counts
    DNA_NUCLEOTIDES.each_with_object({}){ |nucleotide, totals|
      totals[nucleotide] = count(nucleotide)
    }
  end

  private

  def valid_nucleotides
    RNA_NUCLEOTIDES | DNA_NUCLEOTIDES
  end
end
