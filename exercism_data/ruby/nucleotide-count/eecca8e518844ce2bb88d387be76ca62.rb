class DNA
  attr_reader :dna_string
  
  def initialize(dna_string)
    check_validity(dna_string)
    @dna_string = dna_string
  end
  
  def count(nucleotide)
    check_validity(nucleotide)
    dna_string.count(nucleotide)
  end
  
  def nucleotide_counts
    valid_nucleotides.inject({}) do |result_hash, nucleotide| 
      result_hash.store(nucleotide, count(nucleotide))
      result_hash
    end
  end
  
  private
  
  def valid_nucleotides
    ['A', 'T', 'C', 'G']
  end
  
  def check_validity(str)
    str.chars.each {|c| raise ArgumentError unless valid_nucleotides.include?(c)}
  end
end
