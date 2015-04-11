class DNA
  
  def initialize(dna_string)
    DNA.validate_dna(dna_string)
    @dna_string = dna_string
  end
  
  def nucleotide_counts
    "ATCG".chars.each_with_object({}) do |nucleotide,counts|
      counts[nucleotide] = count(nucleotide)
    end
  end
  
  def count(nucleotide)
    return 0 if nucleotide == 'U'
    DNA.validate_dna(nucleotide)
    @dna_string.count(nucleotide)
  end
  
  def self.validate_dna(dna_string)
    raise ArgumentError, "Invalid nucleotide in #{dna_string}" unless dna_string =~ /^[ATCG]*$/
  end
  
end
