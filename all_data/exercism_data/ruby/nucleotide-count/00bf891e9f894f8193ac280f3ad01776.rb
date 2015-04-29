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
    if dna_string =~ /([^#{NUCLEOTIDES.join}])/
      raise ArgumentError, "Invalid nucleotide: #{$1}"
    end
  end

  def ensure_rna_nucleotide(nucleotide)
    unless (NUCLEOTIDES + ["U"]).include? nucleotide
      raise ArgumentError, "Invalid nucleotide #{nucleotide}"
    end
  end

end
