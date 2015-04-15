require 'set'

class DNA  
  def initialize(nucleotides)
    @dna_nucleotide_types = %W(A T C G).to_set
    @rna_nucleotide_types = %W(A T C U).to_set
    @all_nucleotide_types = (@dna_nucleotide_types + @rna_nucleotide_types)
    @nucleotides = nucleotides.chars

    validate!(@dna_nucleotide_types, @nucleotides.to_set)
  end
  
  def count(nucleotide)
    validate!(all_nucleotide_types, Set.new([nucleotide]))
    
    nucleotides.count {|n| n == nucleotide }
  end
  
  def nucleotide_counts
    dna_nucleotide_types.each_with_object(Hash.new(0)) do |n, acc|
      acc[n] = count(n)
    end
  end
  
  private
  
  attr_reader :nucleotides, :dna_nucleotide_types, :rna_nucleotide_types, :all_nucleotide_types
  
  def validate!(valid_types, nucleotides)
    raise ArgumentError unless nucleotides.subset?(valid_types)
  end
end
