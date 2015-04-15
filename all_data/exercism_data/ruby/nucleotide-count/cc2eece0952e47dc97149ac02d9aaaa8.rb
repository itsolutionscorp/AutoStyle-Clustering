require 'set'

class DNA  
  def initialize(nucleotides)
    @dna_nucleotide_types = %W(A T C G).to_set
    @rna_nucleotide_types = %W(A T C U).to_set
    @all_nucleotide_types = (@dna_nucleotide_types + @rna_nucleotide_types)
    @nucleotides          = nucleotides.chars

    validate!(@dna_nucleotide_types, @nucleotides.to_set)
  end
  
  def nucleotide_counts
    Hash[ *dna_nucleotide_types.flat_map {|t| [t, count(t) ]} ]
  end
    
  def count(nucleotide)
    validate!(all_nucleotide_types, [nucleotide].to_set)
    
    nucleotides.count {|n| n == nucleotide }
  end

  private
  
  attr_reader :nucleotides, :dna_nucleotide_types, :rna_nucleotide_types, :all_nucleotide_types
  
  def validate!(valid_types, nucleotides)
    raise ArgumentError unless nucleotides.subset?(valid_types)
  end
end
