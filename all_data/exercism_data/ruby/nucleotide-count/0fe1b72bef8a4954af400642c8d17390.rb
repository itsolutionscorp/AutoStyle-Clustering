class DNA
  
  def initialize(dna_string)
    raise ArgumentError, "Invalid nucleotide in #{dna_string}" unless dna_string =~ /^[ATCG]*$/
    @dna_string = dna_string
  end
  
  def nucleotide_counts
    "ATCG".chars.each_with_object({}) do |nucleotide,counts|
      counts[nucleotide] = count(nucleotide)
    end
  end
  
  def count(nucleotide)
    raise ArgumentError, "Invalid nucleotide #{nucleotide}" unless nucleotide =~ /^[ATCGU]*$/
    @dna_string.count(nucleotide)
  end
end
