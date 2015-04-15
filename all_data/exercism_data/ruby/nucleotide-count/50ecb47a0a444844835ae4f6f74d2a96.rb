class DNA
  def initialize(dna_string)
    validate_dna_string dna_string
    @dna_string = dna_string
  end
  
  def count(type)
    validate_nucleotide_type type
    @dna_string.count type
  end
  
  def nucleotide_counts
    %w(A C G T).each_with_object({}) do |type, counts|
      counts[type] = count type
    end
  end
  
  private
  def validate_nucleotide_type(type)
    raise ArgumentError unless %w(A C G T U).include?(type)    
  end
  
  def validate_dna_string(string)
    raise ArgumentError if string =~ /[^ACGT]/
  end
end
