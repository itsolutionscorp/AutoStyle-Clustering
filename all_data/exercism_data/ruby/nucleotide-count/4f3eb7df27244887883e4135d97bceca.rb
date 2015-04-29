class DNA

  def initialize(dna_string)
    raise ArgumentError unless valid_dna_string?(dna_string)
    @dna_string = dna_string
  end  

  def count(nucleotide)
    raise ArgumentError unless valid_nucleotide?(nucleotide)
    dna_string.count(nucleotide)
  end

  def nucleotide_counts
    nucleotides.each_with_object({}) do |nuc, counts| 
      counts[nuc] = count(nuc) 
    end  
  end

  private

  attr_reader :dna_string

  def nucleotides
    ['A', 'T', 'C', 'G']
  end

  def valid_nucleotide?(nucleotide)
    ['A', 'T', 'C', 'G', 'U'].include?(nucleotide)
  end

  def valid_dna_string?(dna_string)
    dna_string.each_char.all? do |char| 
      nucleotides.include?(char)
    end 
  end
  
end
