class DNA
  
  NUCLEOTIDES = 'GATC'.chars
  
  def initialize(dna)
    @dna = dna
    validate
  end

  def count(nucleotide_to_count)
    validate_nucleotide nucleotide_to_count
    @dna.count(nucleotide_to_count)
  end

  def nucleotide_counts
    counts = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
    NUCLEOTIDES.each_with_object(counts) do |nucleotide, counts|
      counts[nucleotide] = @dna.count(nucleotide)
    end
  end

  private

  def validate
    @dna.chars.each do |nucleotide|
      validate_nucleotide nucleotide
    end
  end

  def validate_nucleotide(nucleotide)
      raise ArgumentError, "invalid nucleotide: #{nucleotide}" unless NUCLEOTIDES.include? nucleotide
  end
end
