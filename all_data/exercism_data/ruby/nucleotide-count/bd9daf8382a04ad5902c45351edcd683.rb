class DNA
  VALID_NUCLEOTIDES = ['A', 'T', 'C', 'G']

  def initialize(nucleotides)
    @nucleotide_counts = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
    ingest_nucleotides_from(nucleotides)
  end

  def nucleotide_counts
    @nucleotide_counts
  end

  def count(nucleotide)
    validate_nucleotide(nucleotide)
    nucleotide_counts[nucleotide]
  end

  private

  def ingest_nucleotides_from(nucleotides)
    nucleotides.split(//).each do |nucleotide|
      add_nucleotide(nucleotide)
    end
  end

  def add_nucleotide(nucleotide)
    validate_nucleotide(nucleotide)
    nucleotide_counts[nucleotide] += 1
  end

  def validate_nucleotide(nucleotide)
    raise ArgumentError unless VALID_NUCLEOTIDES.include? nucleotide
  end
end
