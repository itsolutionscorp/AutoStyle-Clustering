DNA = Struct.new(:strand) do
  def count(nucleotide)
    raise ArgumentError, "Invalid nucleotide: #{nucleotide}" unless valid_nucleotide?(nucleotide)

    nucleotide_counts.fetch(nucleotide) { 0 }
  end

  def nucleotide_counts
    @nucleotide_counts ||= NUCLEOTIDES.each_with_object({}) do |nucleotide, counts|
      counts[nucleotide] = strand.count nucleotide
    end
  end

  private

  def valid_nucleotide?(nucleotide)
    NUCLEOTIDES.include?(nucleotide) || RNA::NUCLEOTIDES.include?(nucleotide)
  end

  NUCLEOTIDES = %w[A C G T]

  class RNA
    NUCLEOTIDES = %w[A C G U]
  end
end
