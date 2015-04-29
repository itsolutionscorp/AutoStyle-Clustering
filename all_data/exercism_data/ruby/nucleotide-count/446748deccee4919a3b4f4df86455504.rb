class DNA

  NUCLEOTIDES = "ATCG".chars

  def initialize(dna_string)
    ensure_valid_nucleotides(dna_string, NUCLEOTIDES)
    @dna_string = dna_string
  end

  def nucleotide_counts
    NUCLEOTIDES.each_with_object({}) do |nucleotide,counts|
      counts[nucleotide] = count(nucleotide)
    end
  end

  def count(nucleotide)
    ensure_valid_nucleotides(nucleotide, NUCLEOTIDES | RNA::NUCLEOTIDES)
    @dna_string.count(nucleotide)
  end

private

  def ensure_valid_nucleotides(dna_string, nucleotides)
    if dna_string =~ /([^#{nucleotides.join}])/
      raise ArgumentError, "Invalid nucleotide: #{$1}"
    end
  end

end

class RNA
  NUCLEOTIDES = "AUCG".chars
end
