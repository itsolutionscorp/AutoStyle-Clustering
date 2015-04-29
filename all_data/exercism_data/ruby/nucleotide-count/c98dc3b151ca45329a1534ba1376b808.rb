require 'pp'

class DNA
  
  attr_reader :nucleotide_counts
  
  def initialize(dna_string)
    raise ArgumentError, "Invalid nucleotide" unless dna_string =~ /^[ATCG]*$/
    @nucleotide_counts = {}
    ['A','T','C','G'].each do |nucleotide|
      @nucleotide_counts[nucleotide] = dna_string.count(nucleotide)
    end
  end
  
  def nucleotide_counts
    @nucleotide_counts
  end
  
  def count(nucleotide)
    return 0 if nucleotide == 'U'
    raise ArgumentError, "Invalid nucleotide" if nucleotide_counts[nucleotide].nil?
    nucleotide_counts[nucleotide]
  end
  
end
