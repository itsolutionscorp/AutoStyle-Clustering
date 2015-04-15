require 'pp'

class DNA
  
  attr_reader :nucleotide_counts
  
  def initialize(dna_string)
    @nucleotide_counts = dna_string.split('').reduce({'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}) do |counts, nocleotide|
      raise ArgumentError, "Invalid nocleotide" if counts[nocleotide].nil? 
      counts[nocleotide] += + 1
      counts
    end
  end
  
  def nucleotide_counts
    @nucleotide_counts
  end
  
  def count(nocleotide)
    return 0 if nocleotide == 'U'
    raise ArgumentError, "Invalid nocleotide" if nucleotide_counts[nocleotide].nil?
    nucleotide_counts[nocleotide]
  end
  
end
