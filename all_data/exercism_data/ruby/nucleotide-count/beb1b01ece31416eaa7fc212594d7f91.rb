class DNA

  NUCLEOTIDES = "ATCG".chars

  def initialize(dna_string)
    ensure_dna_nucleotides(dna_string)
    @dna_string = dna_string
  end

  def nucleotide_counts
    NUCLEOTIDES.each_with_object({}) do |nucleotide,counts|
      counts[nucleotide] = count(nucleotide)
    end
  end

  def count(nucleotide)
    ensure_rna_nucleotide(nucleotide)
    @dna_string.count(nucleotide)
  end

private

  def ensure_dna_nucleotides(dna_string)
    unless (dna_string.chars - NUCLEOTIDES).empty?
      raise ArgumentError, "Invalid nucleotide in #{dna_string}"
    end
  end

  def ensure_rna_nucleotide(nucleotide)
    unless (NUCLEOTIDES + ["U"]).include? nucleotide
      raise ArgumentError, "Invalid nucleotide #{nucleotide}"
    end
  end

end
