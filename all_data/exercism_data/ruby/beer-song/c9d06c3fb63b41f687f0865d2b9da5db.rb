module MolecularBiology
  NUCLEOTIDES = ["A", "G", "C", "T", "U"]
end

class DNA
  attr_reader :strand

  def initialize(strand)
    @strand = strand.split("")
  end

  def nucleotide_counts
    strand.each_with_object(empty_strand_counts) do |element, counts| 
      counts[element] += 1 if valid_nucleotide?(element)
    end
  end

  def count(nucleotide)
    raise ArgumentError unless valid_nucleotide?(nucleotide)
    strand.count { |element| element == nucleotide } 
  end
  
  private

  def empty_strand_counts
    {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
  end

  def valid_nucleotide?(element)
    MolecularBiology::NUCLEOTIDES.include? element
  end
end
